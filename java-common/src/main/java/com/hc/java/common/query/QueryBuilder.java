package com.hc.java.common.query;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryBuilder implements Query {

    private List<QueryGroup> groups;

    private QueryBuilder() {

    }

    public static QueryBuilder build() {
        QueryBuilder builder = new QueryBuilder();
        builder.groups = new ArrayList<>();
        return builder;
    }

    public QueryGroup and() {
        QueryGroup group = new QueryGroup(Relation.AND);
        groups.add(group);
        return group;
    }

    public QueryGroup or() {
        QueryGroup group = new QueryGroup(Relation.OR);
        groups.add(group);
        return group;
    }

    public QueryBuilder andCriteria(String property, Object value) {
        andCriteria(MatchType.EQ, property, value);
        return this;
    }

    public QueryBuilder orCriteria(String property, Object value) {
        orCriteria(MatchType.EQ, property, value);
        return this;
    }

    public QueryBuilder andCriteria(MatchType matchType, String property, Object value) {
        if (CollectionUtils.isEmpty(groups)) {
            groups = new ArrayList<>();
            groups.add(new QueryGroup());
        }
        QueryGroup group = groups.get(groups.size() - 1);
        group.and(property, value, matchType);
        return this;
    }

    public QueryBuilder orCriteria(MatchType matchType, String property, Object value) {
        if (CollectionUtils.isEmpty(groups)) {
            groups = new ArrayList<>();
            groups.add(new QueryGroup());
        }
        QueryGroup group = groups.get(groups.size() - 1);
        group.or(property, value, matchType);
        return this;
    }

    public QueryBuilder addCriteriaFromMap(Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                this.andCriteria(key, params.get(key));
            }
        }
        return this;
    }

    @Override
    public List<QueryGroup> getGroups() {
        return groups;
    }
}
