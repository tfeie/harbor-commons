package com.the.harbor.commons.indices.hyuniversity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
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

    /**
     * 批量添加索引
     * @param list
     * @author zhangchao
     */
    public static void batchAddIndex(List<University> list) {
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
                    .prepareIndex(HarborIndex.HY_COMMON_DB.getValue(),
                            HarborIndexType.HY_UNIVERSITY.getValue(), u.getUniversityId())
                    .setSource(jsonSource));
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        System.out.println(bulkResponse.hasFailures());
    }

    /**
     * 根据大学名称做搜索建议
     * 
     * @param universityName
     * @return
     * @author zhangchao
     */
    public static List<String> querySuggestByUniversityName(String universityName) {
        CompletionSuggestionBuilder suggestionsBuilder = new CompletionSuggestionBuilder("complete");
        suggestionsBuilder.text(universityName);
        suggestionsBuilder.field("universityNameSuggest");
        suggestionsBuilder.size(10);
        SuggestResponse resp = ElasticSearchFactory.getClient()
                .prepareSuggest(HarborIndex.HY_COMMON_DB.getValue())
                .addSuggestion(suggestionsBuilder).execute().actionGet();
        List<? extends Entry<? extends Option>> list = resp.getSuggest().getSuggestion("complete")
                .getEntries();
        List<String> suggests = new ArrayList<String>();
        if (list == null) {
            return null;
        } else {
            for (Entry<? extends Option> e : list) {
                for (Option option : e) {
                    suggests.add(option.getText().toString());
                }
            }
            return suggests;
        }
    }

}
