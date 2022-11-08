package com.hc.java.common.query;

import java.util.List;

public interface Query {

    /**
     * 返回查询分组对象列表
     *
     * @return
     */
    List<QueryGroup> getGroups();
}
