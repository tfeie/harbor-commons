package com.the.harbor.test;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.the.harbor.base.enumeration.dict.ParamCode;
import com.the.harbor.base.enumeration.dict.TypeCode;
import com.the.harbor.commons.redisdata.def.HyDictsVo;
import com.the.harbor.commons.redisdata.util.HyDictUtil;

public class RedisTest {

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			List<HyDictsVo> list =HyDictUtil.getHyDicts(TypeCode.HY_USER.getValue(),ParamCode.USER_TYPE.getValue());
			System.out.println(JSON.toJSONString(list));
		}

	}

}
