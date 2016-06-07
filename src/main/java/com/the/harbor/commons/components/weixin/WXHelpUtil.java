package com.the.harbor.commons.components.weixin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.HttpUtil;
import com.the.harbor.commons.util.RandomUtil;
import com.the.harbor.commons.util.StringUtil;

public final class WXHelpUtil {

	private WXHelpUtil() {

	}

	/**
	 * 获取微信普通的access_token<br>
	 * 必须放在全局服务器中，避免业务点各自去刷新造成冲突<br>
	 * 
	 * @return
	 */
	public static String getCommonAccessToken() {
		String token = CacheFactory.getClient().get(RedisDataKey.KEY_WEIXIN_COMMON_TOKEN.getKey());
		if (StringUtil.isBlank(token)) {
			// 如果TOKEN不存在，则重新获取
			String url = GlobalSettings.getWeiXinTokenAPI() + "?grant_type=client_credential&appid="
					+ GlobalSettings.getWeiXinAppId() + "&secret=" + GlobalSettings.getWeiXinAppSecret();
			JSONObject jsonObject = HttpUtil.httpsRequest(url, "GET", null);
			if (jsonObject != null) {
				if (jsonObject.containsKey("errcode")) {
					throw new SDKException("获取微信普通的ACCESS_TOKEN失败，错误码:" + jsonObject.getString("errorCode"));
				}
				token = jsonObject.getString("access_token");
				int expiresin = jsonObject.getIntValue("expires_in");
				CacheFactory.getClient().set(RedisDataKey.KEY_WEIXIN_COMMON_TOKEN.getKey(), token);
				CacheFactory.getClient().expire(RedisDataKey.KEY_WEIXIN_COMMON_TOKEN.getKey(), expiresin);
			}
		}
		if (StringUtil.isBlank(token)) {
			throw new SDKException("获取不到微信普通的ACCESS_TOKEN");
		}
		return token;
	}

	/**
	 * 获取微信JSAPI的TICKET
	 * 
	 * @return
	 */
	public static String getJSAPITicket() {
		String ticket = CacheFactory.getClient().get(RedisDataKey.KEY_WEIXIN_TICKET.getKey());
		if (StringUtil.isBlank(ticket)) {
			String accessToken = getCommonAccessToken();
			String url = GlobalSettings.getWeiXinJSAPITicketAPI() + "?access_token=" + accessToken + "&type=jsapi";
			JSONObject jsonObject = HttpUtil.httpsRequest(url, "GET", null);
			if (jsonObject != null) {
				String errcode = jsonObject.getString("errcode");
				String errmsg = jsonObject.getString("errmsg");
				if (!"0".equals(errcode)) {
					throw new SDKException("获取微信ticket失败:" + errmsg);
				}
				ticket = jsonObject.getString("ticket");
				int expires_in = jsonObject.getIntValue("expires_in");
				CacheFactory.getClient().set(RedisDataKey.KEY_WEIXIN_TICKET.getKey(), ticket);
				CacheFactory.getClient().expire(RedisDataKey.KEY_WEIXIN_TICKET.getKey(), expires_in);
			}
		}
		if (StringUtil.isBlank(ticket)) {
			throw new SDKException("获取不到微信的JSAPI_TICKET");
		}
		return ticket;
	}

	/**
	 * 生成随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String createNoncestr() {
		String res = RandomUtil.generateString(16);
		return res;
	}

	/**
	 * 采用SHA算法生成JSSDK的签名信息<br>
	 * 签名用的noncestr和timestamp必须与wx.config中的nonceStr和timestamp相同。<br>
	 * 签名用的url必须是调用JS接口页面的完整URL。<br>
	 * 
	 * @param noncestr
	 * @param jsapiTicket
	 * @param timestamp
	 * @param url
	 * @return
	 */
	public static String createJSSDKSignatureSHA(String noncestr, String jsapiTicket, long timestamp, String url) {
		if (StringUtil.isBlank(noncestr)) {
			throw new SDKException("生成JSSDK签名失败:缺少随机数");
		}
		if (StringUtil.isBlank(jsapiTicket)) {
			throw new SDKException("生成JSSDK签名失败:缺少TICKET");
		}
		if (StringUtil.isBlank(url)) {
			throw new SDKException("生成JSSDK签名失败:缺少URL");
		}
		String str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url
				+ "";
		String signature = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new SDKException("生成JSSDK签名失败", e);
		} catch (UnsupportedEncodingException e) {
			throw new SDKException("生成JSSDK签名失败", e);
		}
		return signature;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static void main(String[] agrs) {
		String noncestr = WXHelpUtil.createNoncestr();
		String jsapiTicket = WXHelpUtil.getJSAPITicket();
		long timestamp = DateUtil.getCurrentTimeMillis();
		String url = "http://localhost:8080/hh/hh";
		// String signature = WXHelpUtil.createJSSDKSignatureSHA(noncestr,
		// jsapiTicket, timestamp, url);
		System.out.println(DateUtil.getCurrentTimeMillis());
	}

}
