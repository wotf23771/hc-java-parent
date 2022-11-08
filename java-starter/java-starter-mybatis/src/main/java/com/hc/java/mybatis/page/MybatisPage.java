package com.hc.java.mybatis.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装
 * <p>
 * 注意：所有序号从1开始.
 *
 * @author wangxiaolei
 * @date 2020/9/22 12:50
 */
public class MybatisPage<T> implements IPage {

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

    protected String orderBy = null;

    protected String order = null;

    private com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> _page;

    // -- 构造函数 --//
    public MybatisPage() {
        this(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE);
    }

    public MybatisPage(long pageSize) {
        this(DEFAULT_PAGE_NO, pageSize);
    }

    public MybatisPage(long pageNo, long pageSize) {
        _page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>();
        _page.setCurrent(pageNo);
        _page.setSize(pageSize);
    }

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public long getPageNo() {
        return _page.getCurrent();
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final long pageNo) {
        _page.setCurrent(pageNo);
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public long getPageSize() {
        return _page.getSize();
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final long pageSize) {
        _page.setSize(pageSize);
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
        return _page.getRecords();
    }

    /**
     * 设置页内的记录列表.
     */
    public void setList(final List<T> list) {
        _page.setRecords(list);
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotalCount() {
        return _page.getTotal();
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final long totalCount) {
        _page.setTotal(totalCount);
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

    @Override
    public List<OrderItem> orders() {
        List<OrderItem> itemList = new ArrayList<>();
        if (StringUtils.isNoneBlank(orderBy) && StringUtils.isNotBlank(order)) {
            String[] orderBy_array = StringUtils.split(orderBy, ",");
            String[] order_array = StringUtils.split(order, ",");

            // 判断 order 、 orderBy 长度是否一致
            if (orderBy_array != null && order_array != null && order_array.length == orderBy_array.length) {
                int length = order_array.length;
                // 循环排序条件
                for (int i = 0; i < length; i++) {
                    // 获得排序类型
                    String sort = order_array[i];
                    if (ORDER_ASC.equalsIgnoreCase(sort)) {
                        // 设置升序条件
                        itemList.add(OrderItem.asc(orderBy_array[i]));
                    } else if (ORDER_DESC.equalsIgnoreCase(sort)) {
                        // 设置降序条件
                        itemList.add(OrderItem.desc(orderBy_array[i]));
                    }
                }
            }
        }
        return itemList;
    }

    @JsonIgnore
    @Override
    public List getRecords() {
        return _page.getRecords();
    }

    @Override
    public IPage setRecords(List records) {
        return _page.setRecords(records);
    }

    @JsonIgnore
    @Override
    public long getTotal() {
        return _page.getTotal();
    }

    @Override
    public IPage setTotal(long total) {
        return _page.setTotal(total);
    }

    @JsonIgnore
    @Override
    public long getSize() {
        return _page.getSize();
    }

    @Override
    public IPage setSize(long size) {
        return _page.setSize(size);
    }

    @JsonIgnore
    @Override
    public long getCurrent() {
        return _page.getCurrent();
    }

    @Override
    public IPage setCurrent(long current) {
        return _page.setCurrent(current);
    }

    @JsonIgnore
    @Override
    public long getPages() {
        return _page.getPages();
    }

}
