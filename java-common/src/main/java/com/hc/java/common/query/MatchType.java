package com.hc.java.common.query;

/**
 * 查询匹配类型
 */
public enum MatchType {

    EQ("=", "等于"),
    GT(">", "大于"),
    LT("<", "小于"),
    LT_EQ("<=", "小于等于"),
    GT_EQ(">=", "大于等于"),
    LIKE("like", "模糊匹配"),
    NOT_LIKE("not like", "不模糊匹配"),
    NOT_EQ("<>", "不等于");

    private String expression;

    private String desc;

    MatchType(String expression, String desc) {
        this.expression = expression;
        this.desc = desc;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
