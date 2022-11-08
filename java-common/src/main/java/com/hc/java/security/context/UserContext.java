package com.hc.java.security.context;

import lombok.Data;

/**
 * 当前用户上下文
 *
 * @author wangxiaolei
 * @since 2022/3/24 0:29
 */
public class UserContext {

    public static InheritableThreadLocal<User> context = new InheritableThreadLocal<>();

    /**
     * 初始化当前用户上下文
     *
     * @param contextId 用户上下文ID
     * @param userId    用户ID
     * @param userName  用户姓名
     */
    public static void init(String contextId, String userId, String userName) {
        context.remove();
        User user = new User();
        user.setContextId(contextId);
        user.setUserId(userId);
        user.setUserName(userName);
        context.set(user);
    }

    public static void init(User user) {
        context.remove();
        context.set(user);
    }

    public static User get() {
        return context.get();
    }

    public static String getUserId() {
        User user = get();
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }

    public static void clear() {
        context.remove();
    }

    @Data
    public static class User {

        /**
         * 用户上下文ID
         */
        private String contextId;

        /**
         * 用户ID
         */
        private String userId;

        /**
         * 用户姓名
         */
        private String userName;

    }
}
