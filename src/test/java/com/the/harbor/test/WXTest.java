package com.the.harbor.test;

import com.the.harbor.commons.components.weixin.WXHelpUtil;
import com.the.harbor.commons.util.DateUtil;

public class WXTest {

	public static void main(String[] args) {
		String nonceStr = WXHelpUtil.createNoncestr();
		String pkg = WXHelpUtil.getPackageOfWXJSSDKChoosePayAPI("海归海湾", "201606100009921", 1, "192.168.1.1",
				"oztCUs_Ci25lT7IEMeDLtbK6nr1M", "http://localhost:8080/u/p", nonceStr);
		String sign =WXHelpUtil.getPaySignOfWXJSSDKChoosePayAPI(DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS), nonceStr, pkg);
		System.out.println(sign);
	}

}
