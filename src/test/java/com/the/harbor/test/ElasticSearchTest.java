package com.the.harbor.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.indices.hyuniversity.University;
import com.the.harbor.commons.indices.hyuniversity.UniversityHandler;

public class ElasticSearchTest {

    public static void main(String[] args) throws IOException {

        // ElasticSearchFactory.getClient().admin().indices()
        // .prepareDelete(HarborIndex.HY_UNIVERSITY.getName()).execute().actionGet();

        // UniversityMappingBuilder.createUniversityMapping();
        List<University> list = new ArrayList<University>();
        list.add(new University("1", "中国政法大学", "CN"));
        list.add(new University("2", "中国人民大学", "CN"));
        list.add(new University("3", "中南大学", "CN"));
        list.add(new University("4", "中南民族大学", "CN"));
        // UniversityHandler.addIndex(list);

        List<String> l = UniversityHandler.querySuggestByUniversityName("中国");
        System.out.println(JSON.toJSONString(l));

    }

}
