package com.hc.java.common.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装
 * <p>
 * 注意：所有序号从1开始.
 *
 * @author wangxiaolei
 * @date 2020/9/22 12:50
 */
public class Page<T> {

    public static String ORDER_ASC = "asc";

    public static String ORDER_DESC = "desc";

    /**
     * 默认当前页码
     */
    public static final long DEFAULT_PAGE_NO = 1;

    /**
     * 默认每页条数
     */
    public static final long DEFAULT_PAGE_SIZE = 10;

    protected long pageNo = DEFAULT_PAGE_NO;

    private long pageSize = DEFAULT_PAGE_SIZE;

    protected long totalCount;

    protected List<T> list;

    /**
     * 排序字段，多个以英文逗号隔开。例如：name,age
     */
    protected String orderBy = null;

    /**
     * 排序条件，多个以英文逗号隔开。例如：asc,desc
     */
    protected String order = null;

    // -- 构造函数 --//
    public Page() {
    }

    public Page(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public long getPageNo() {
        return this.pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final long pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获得每页的记录数量, 默认为10.
     */
    public long getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public long getFirst() {
        return ((getPageNo() - 1) * getPageSize()) + 1;
    }

    // -- 访问查询结果函数 --//

    /**
     * 获得页内的记录列表.
     */
    @JsonIgnore
    public List<T> getList() {
        return this.list;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setList(final List<T> list) {
        this.list = list;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotalCount() {
        return this.totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final long totalCount) {
        this.totalCount = this.totalCount;
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPage() {
        if (getTotalCount() < 0) {
            return -1;
        }
        long count = getTotalCount() / getPageSize();
        if (getTotalCount() % getPageSize() > 0) {
            count++;
        }
        return count;
    }

    @JsonIgnore
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @JsonIgnore
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public static void main(String[] args) {
        String order = "";
        String[] array = StringUtils.split(order, ",");
        Arrays.stream(array).forEach(System.out::println);
    }

}
