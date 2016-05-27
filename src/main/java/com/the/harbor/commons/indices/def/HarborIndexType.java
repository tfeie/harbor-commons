package com.the.harbor.commons.indices.def;

public enum HarborIndexType {

    DETAIL("detail", "详细数据");

    private HarborIndexType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * 索引名称
     */
    private String name;

    /**
     * 索引描述
     */
    private String desc;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}
