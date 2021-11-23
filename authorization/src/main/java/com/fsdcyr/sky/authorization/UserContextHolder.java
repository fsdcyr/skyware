package com.fsdcyr.sky.authorization;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-22 5:42 下午
 */
public final class UserContextHolder {

    private static ThreadLocal<UserContext> USER_CONTEXT = ThreadLocal.withInitial(() -> {
        return null;
    });

    public static UserContext getUserContext() {
        return USER_CONTEXT.get();
    }

    public static void save(UserContext userContext) {
        USER_CONTEXT.set(userContext);
    }


}
