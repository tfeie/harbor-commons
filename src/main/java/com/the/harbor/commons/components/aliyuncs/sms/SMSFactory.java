package com.the.harbor.commons.components.aliyuncs.sms;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

public final class SMSFactory {

    public static TaobaoClient getTaobaoClient() {
        String url = SMSSettings.getURL();
        String appkey = SMSSettings.getAppKey();
        String secret = SMSSettings.getAppSecret();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        return client;

    }

}
