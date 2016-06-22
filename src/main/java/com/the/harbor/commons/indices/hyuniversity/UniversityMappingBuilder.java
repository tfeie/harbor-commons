package com.the.harbor.commons.indices.hyuniversity;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;

public final class UniversityMappingBuilder {

	public static void createUniversityMapping() throws IOException {
		if (!ElasticSearchFactory.doesIndexExist(HarborIndex.HY_COMMON_DB.getValue())) {
			ElasticSearchFactory.getClient().admin().indices().prepareCreate(HarborIndex.HY_COMMON_DB.getValue())
					.execute().actionGet();
		}
		if (ElasticSearchFactory.doesMappingExist(HarborIndex.HY_COMMON_DB.getValue(),
				HarborIndexType.HY_UNIVERSITY.getValue())) {
			ElasticSearchFactory.getClient().admin().indices().prepareGetMappings(HarborIndex.HY_COMMON_DB.getValue())
					.addTypes(HarborIndexType.HY_UNIVERSITY.getValue()).execute().actionGet();
		}

		XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
				.startObject(HarborIndexType.HY_UNIVERSITY.getValue()).startObject("properties")

				.startObject("universityId").field("type", "string").field("store", "yes")
				.field("index", "not_analyzed").endObject()

				.startObject("universityName").field("type", "string").field("store", "yes").endObject()

				.startObject("countryCode").field("type", "string").field("store", "yes").field("index", "not_analyzed")
				.endObject()

				.startObject("universityNameSuggest").field("type", "completion").field("analyzer", "standard")
				.field("payloads", true).field("preserve_separators", false)
				.field("preserve_position_increments", false).field("max_input_length", 100).endObject()

				.endObject().endObject().endObject();
		ElasticSearchFactory.getClient().admin().indices().preparePutMapping()
				.setIndices(HarborIndex.HY_COMMON_DB.getValue()).setType(HarborIndexType.HY_UNIVERSITY.getValue())
				.setSource(builder).execute().actionGet();
	}

}
