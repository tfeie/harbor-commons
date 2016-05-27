package com.the.harbor.commons.indices.hyuniversity;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.suggest.SuggestRequestBuilder;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.util.CollectionUtil;

public final class UniversityHandler {

    public static void addIndex(List<University> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        Gson gson = new Gson();
        BulkRequestBuilder bulkRequest = ElasticSearchFactory.getClient().prepareBulk();
        for (University u : list) {
            List<String> input = new ArrayList<String>();
            input.add(u.getUniversityId());
            input.add(u.getUniversityName());
            input.add(u.getCountryCode());
            Map<Object, Object> payload = new HashMap<Object, Object>();
            payload.put("universityId", u.getUniversityId());
            payload.put("universityName", u.getUniversityName());
            payload.put("countryCode", u.getCountryCode());
            payload.put("type", "university");
            UniversityNameSuggest nameSuggest = new UniversityNameSuggest(input, payload);
            u.setUniversityNameSuggest(nameSuggest);
            JsonObject jo = (JsonObject) gson.toJsonTree(u);
            String jsonSource = gson.toJson(jo);
            bulkRequest.add(ElasticSearchFactory
                    .getClient()
                    .prepareIndex(HarborIndex.HY_UNIVERSITY.getName(),
                            HarborIndexType.DETAIL.getName(), u.getUniversityId())
                    .setSource(jsonSource));
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        System.out.println(bulkResponse.hasFailures());
    }

    public static List<University> queryByUniversityName(String countryCode,String universityName) {
        SuggestRequestBuilder srb = ElasticSearchFactory.getClient().prepareSuggest(
                HarborIndex.HY_UNIVERSITY.getName());
        CompletionSuggestionBuilder csfb = new CompletionSuggestionBuilder("universityNameSuggest")
                .field("universityNameSuggest").text(universityName).size(10);
        srb = srb.addSuggestion(csfb); 
        SuggestResponse response = srb.execute().actionGet();
        List<? extends Entry<? extends Option>> list = response.getSuggest()
                .getSuggestion("universityNameSuggest").getEntries();
        for (Entry<? extends Option> e : list) {
            for (Option option : e) {
                System.out.println(option.getText());
            }

        }

        return null;

    }

}
