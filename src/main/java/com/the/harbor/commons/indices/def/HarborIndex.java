package com.the.harbor.commons.indices.def;

public enum HarborIndex {

    HY_UNIVERSITY("hy_university", "海外学校数据索引");

    private HarborIndex(String name, String desc) {
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
