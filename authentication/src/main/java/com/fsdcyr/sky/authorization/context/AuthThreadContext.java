package com.fsdcyr.sky.authorization.context;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-22 5:42 下午
 */
public final class AuthThreadContext {

    private static InheritableThreadLocal<AuthContext> AUTH_CONTEXT = new InheritableThreadLocal<>();

    public static AuthContext getAuthContext() {
        return AUTH_CONTEXT.get();
    }

    public static void bind(AuthContext authContext) {
        AUTH_CONTEXT.set(authContext);
    }

    public static void unbind() {
        AUTH_CONTEXT.remove();
    }


}
