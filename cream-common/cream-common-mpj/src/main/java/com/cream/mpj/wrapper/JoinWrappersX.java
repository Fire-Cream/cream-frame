package com.cream.mpj.wrapper;

import com.github.yulichang.toolkit.JoinWrappers;

/**
 * JoinWrappers 拓展实现
 *
 * @author Cream
 * @since 2026-06-09 23:01
 */
public class JoinWrappersX extends JoinWrappers {

    /**
     * JoinWrappers.lambda()
     */
    public static <T> MPJLambdaWrapperX<T> lambda() {
        return new MPJLambdaWrapperX<>();
    }

    /**
     * JoinWrappers.lambda(alias)
     */
    public static <T> MPJLambdaWrapperX<T> lambda(String alias) {
        return new MPJLambdaWrapperX<>(alias);
    }

    /**
     * JoinWrappers.lambda(clazz)
     */
    public static <T> MPJLambdaWrapperX<T> lambda(Class<T> clazz) {
        return new MPJLambdaWrapperX<>(clazz);
    }

    /**
     * JoinWrappers.lambda(alias, clazz)
     */
    public static <T> MPJLambdaWrapperX<T> lambda(String alias, Class<T> clazz) {
        return new MPJLambdaWrapperX<>(clazz, alias);
    }

    /**
     * JoinWrappers.lambda(entity)
     */
    public static <T> MPJLambdaWrapperX<T> lambda(T entity) {
        return new MPJLambdaWrapperX<>(entity);
    }

    /**
     * JoinWrappers.lambda(alias, entity)
     */
    public static <T> MPJLambdaWrapperX<T> lambda(String alias, T entity) {
        return new MPJLambdaWrapperX<>(entity, alias);
    }

}
