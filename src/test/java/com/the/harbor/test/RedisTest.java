package com.the.harbor.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.components.globalconfig.MemeberPrice;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;

public class RedisTest {

	public static void main(String[] args) {
		ICacheClient client = CacheFactory.getClient();
		client.set("hello", "你好");
		System.out.println(client.get("hello"));
		List<MemeberPrice> l = GlobalSettings.getMemeberPrices();
		MemeberPrice p = new MemeberPrice();
		p.setMonths(1);
		p.setPrices(1);
		l.add(p);
		p = new MemeberPrice();
		p.setMonths(2);
		p.setPrices(2);
		l.add(p);
		p = new MemeberPrice();
		p.setMonths(2);
		p.setPrices(2);
		l.add(p);
		System.out.println(JSON.toJSONString(l));
	}

}
