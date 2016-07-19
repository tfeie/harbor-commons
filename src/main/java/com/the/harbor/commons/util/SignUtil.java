package com.the.harbor.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.components.weixin.WXHelpUtil;
import com.the.harbor.commons.exception.SDKException;

public class SignUtil {

	 public static String getUserInviteSign(String userId,String inviteCode) {
		String privateKey = "zhousf";//GlobalSettings.getPrivateKey();
		String str = "inviteCode=" + inviteCode + "&privateKey=" + privateKey + "&userId=" + userId;
		String signature = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new SDKException("生成签名失败", e);
		} catch (UnsupportedEncodingException e) {
			throw new SDKException("生成签名失败", e);
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
}
