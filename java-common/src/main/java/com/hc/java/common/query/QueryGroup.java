package com.hc.java.common.query;

import java.util.ArrayList;
import java.util.List;

public class QueryGroup {

    private List<QueryCriteria> criteriaList;

    private Relation relation = Relation.AND;

    public QueryGroup() {
        criteriaList = new ArrayList<>();
    }

    public QueryGroup(Relation relation) {
        this.relation = relation;
        criteriaList = new ArrayList<>();
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public List<QueryCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setConditions(List<QueryCriteria> conditions) {
        this.criteriaList = conditions;
    }

    public void or(String property, Object value) {
        QueryCriteria cond = new QueryCriteria(property, value);
        cond.setRelation(Relation.OR);
        criteriaList.add(cond);
    }

    public void or(String property, Object value, MatchType matchType) {
        QueryCriteria cond = new QueryCriteria(property, value, matchType);
        cond.setRelation(Relation.OR);
        criteriaList.add(cond);
    }

    public void and(String property, Object value) {
        QueryCriteria cond = new QueryCriteria(property, value);
        cond.setRelation(Relation.AND);
        criteriaList.add(cond);
    }

    public void and(String property, Object value, MatchType matchType) {
        QueryCriteria cond = new QueryCriteria(property, value, matchType);
        cond.setRelation(Relation.AND);
        criteriaList.add(cond);
    }
}
