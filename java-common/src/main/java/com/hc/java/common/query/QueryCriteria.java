package com.hc.java.common.query;

public class QueryCriteria {

    private String property;

    private Object value;

    private MatchType matchType = MatchType.EQ;

    private Relation relation = Relation.AND;

    public QueryCriteria() {
    }

    public QueryCriteria(Relation relation) {
        this.relation = relation;
    }

    public QueryCriteria(String property, Object value) {
        this.property = property;
        this.value = value;
    }

    public QueryCriteria(String property, Object value, MatchType matchType) {
        this.property = property;
        this.value = value;
        this.matchType = matchType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Relation getRelation() {
        return relation;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
