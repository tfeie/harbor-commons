package com.the.harbor.commons.indices.hyuniversity;

import java.util.List;
import java.util.Map;

public class UniversityNameSuggest {

    private List<String> input;

    private Map<Object, Object> payload;

    public UniversityNameSuggest(List<String> input, Map<Object, Object> payload) {
        this.input = input;
        this.payload = payload;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public Map<Object, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<Object, Object> payload) {
        this.payload = payload;
    }

}
