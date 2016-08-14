package com.the.harbor.commons.components.aliyuncs.im;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

public final class IMFactory {

    public static TaobaoClient getTaobaoClient() {
        String url = IMSettings.getURL();
        String appkey = IMSettings.getAppKey();
        String secret = IMSettings.getAppSecret();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        return client;

    }

}
