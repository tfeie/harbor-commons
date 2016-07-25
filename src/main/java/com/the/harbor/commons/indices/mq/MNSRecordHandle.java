package com.the.harbor.commons.indices.mq;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;

public class MNSRecordHandle {

	public static void addMNSRecord(MNSRecord mns) {
		if (mns == null) {
			return;
		}
		if (StringUtil.isBlank(mns.getMqId())) {
			return;
		}
		if (StringUtil.isBlank(mns.getMqType())) {
			return;
		}
		try {
			String id = mns.getMqType() + "." + mns.getMqId();
			mns.setStatus("INIT");
			mns.setSendDate(DateUtil.getDateString(DateUtil.DATETIME_FORMAT));
			ElasticSearchFactory.addIndex(HarborIndex.HY_MNS_DB.getValue(), HarborIndexType.HY_MNS_DATA.getValue(), id,
					JSON.toJSONString(mns));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void processMNSRecord(String mqId, String mqType, boolean result, String error) {
		if (StringUtil.isBlank(mqId)) {
			return;
		}
		if (StringUtil.isBlank(mqType)) {
			return;
		}
		try {
			String id = mqType + "." + mqId;
			ElasticSearchFactory.getClient();
			Client client = ElasticSearchFactory.getClient();
			SearchResponse response = client.prepareSearch(HarborIndex.HY_MNS_DB.getValue())
					.setTypes(HarborIndexType.HY_MNS_DATA.getValue()).setQuery(QueryBuilders.termQuery("_id", id))
					.execute().actionGet();
			if (response.getHits().totalHits() == 0) {
				return;
			}
			MNSRecord mns = JSON.parseObject(response.getHits().getHits()[0].getSourceAsString(), MNSRecord.class);
			mns.setStatus(result ? "CONSUMER_SUCCESS" : "CONSUMER_FAIL");
			mns.setError(error);
			mns.setConsumeDate(DateUtil.getDateString(DateUtil.DATETIME_FORMAT));
			client.prepareIndex(HarborIndex.HY_MNS_DB.getValue().toLowerCase(),
					HarborIndexType.HY_MNS_DATA.getValue().toLowerCase(), id).setRefresh(true)
					.setSource(JSON.toJSONString(mns)).execute().actionGet();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
