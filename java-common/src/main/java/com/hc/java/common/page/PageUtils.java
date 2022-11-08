package com.hc.java.common.page;

import java.util.List;

/**
 * 分页工具类
 *
 * @author wangxiaolei
 * @since 2022/9/26 11:24
 */
public class PageUtils {

    /**
     * 内存分页
     *
     * @param page 请求分页对象
     * @param list 原数据
     * @param <T>
     * @return 内页结果，将分页后的数据保存到 page 对象
     */
    public static <T> Page<T> buildPage(Page<T> page, List<T> list) {
        page.setTotalCount(list.size());
        long pageNo = page.getPageNo();
        long pageSize = page.getPageSize();
        int maxIndex = list.size();
        long from = (pageNo - 1) * pageSize;
        if (from >= maxIndex) {
            from = 0;
        }
        long to = from + pageSize;
        if (to >= maxIndex) {
            to = maxIndex;
        }
        list = list.subList((int) from, (int) to);
        page.setList(list);
        return page;
    }
}
