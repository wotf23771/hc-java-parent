package com.hc.java.common.query;

import org.apache.commons.lang3.StringUtils;

/**
 * 简单条件
 *
 * @author wangxiaolei
 * @since 2022/1/1 22:06
 */
public class SimpleCondition {

    private Object value;

    private String match;

    private String expression = "=";

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getExpression() {
        if (StringUtils.isNotBlank(match)) {
            switch (match) {
                case "EQ":
                    expression = MatchType.EQ.getExpression();
                    break;
                case "GT":
                    expression = MatchType.GT.getExpression();
                    break;
                case "LT":
                    expression = MatchType.LT.getExpression();
                    break;
                case "LT_EQ":
                    expression = MatchType.LT_EQ.getExpression();
                    break;
                case "GT_EQ":
                    expression = MatchType.GT_EQ.getExpression();
                    break;
                case "LIKE":
                    expression = MatchType.LIKE.getExpression();
                    break;
                case "NOT_LIKE":
                    expression = MatchType.NOT_LIKE.getExpression();
                    break;
                case "NOT_EQ":
                    expression = MatchType.NOT_EQ.getExpression();
                    break;
            }
        }
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
