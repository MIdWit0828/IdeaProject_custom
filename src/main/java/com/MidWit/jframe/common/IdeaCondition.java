package com.MidWit.jframe.common;

public class IdeaCondition {
    private String condition;
    private String value;

    public IdeaCondition(String option, String value) {
        this.condition = option;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public String getValue() {
        return value;
    }
}
