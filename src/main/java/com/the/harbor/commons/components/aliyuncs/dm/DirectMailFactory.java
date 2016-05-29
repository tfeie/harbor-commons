package com.the.harbor.commons.components.aliyuncs.dm;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public final class DirectMailFactory {

    public static IAcsClient getIAcsClient() {
        IClientProfile profile = DefaultProfile.getProfile(DirectMailSettings.getDMRegionId(),
                DirectMailSettings.getAccesskeyId(), DirectMailSettings.getAccesskeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

}
