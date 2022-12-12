package com.hc.java.mybatis.page;

import com.hc.java.common.page.Page;

/**
 * Mybatis 分页转换类，与公共 Page对象进行转换
 *
 * @author wangxiaolei
 * @since 2022/11/2 17:32
 */
public class PageWrapper {

    /**
     * 将公共 Page 对象转换为 将MybatisPage 对象
     *
     * @param page 公共 Page 对象
     * @param <T>  分页对象中数据类型
     * @return MybatisPage 对象
     */
    public static <T> MybatisPage<T> wrapper(Page<T> page) {
        if (page == null) {
            return null;
        }
        MybatisPage mybatisPage = new MybatisPage<>();
        mybatisPage.setPageNo(page.getPageNo());
        mybatisPage.setPageSize(page.getPageSize());
        mybatisPage.setOrderBy(page.getOrderBy());
        mybatisPage.setOrder(page.getOrder());
        return mybatisPage;
    }

    /**
     * 将 MybatisPage 对象 转换为 公共 Page 对象
     *
     * @param mybatisPage MybatisPage 对象
     * @param <T>         分页对象中数据类型
     * @return 公共 Page 对象
     */
    public static <T> Page<T> unWrapper(MybatisPage<T> mybatisPage) {
        if (mybatisPage == null) {
            return null;
        }
        Page<T> page = new Page<>(mybatisPage.getPageNo(), mybatisPage.getPageSize());
        page.setPageNo(mybatisPage.getPageNo());
        page.setPageSize(mybatisPage.getPageSize());
        page.setTotalCount(mybatisPage.getTotalCount());
        page.setList(mybatisPage.getList());
        return page;
    }
}
