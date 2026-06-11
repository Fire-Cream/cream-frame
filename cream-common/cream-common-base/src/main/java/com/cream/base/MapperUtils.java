package com.cream.base;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 实体类转换工具
 *
 * @author Cream
 * @since 2026-06-09 23:11
 */
@Slf4j
public class MapperUtils {

    public static <T, U> List<U> mappingList(final List<T> source, final Class<U> destType) {
        if (source == null) {
            return null;
        } else {
            return source.stream().filter(Objects::nonNull).map((v2) -> mapping(v2, destType)).collect(Collectors.toList());
        }
    }

    public static <T, U> U mapping(T source, final Class<U> destType) {
        if ("java.lang.Object".equals(destType.getName())) {
            return destType.cast(source);
        } else {
            try {
                return copying(source, destType.newInstance());
            } catch (Exception e) {
                log.error("对象转换失败！", e);
                return null;
            }
        }
    }

    public static <T, U> U copying(T source, U dest) {
        if (source == null) {
            return null;
        } else {
            BeanUtils.copyProperties(source, dest);
            return dest;
        }
    }

    /**
     * @param entity 要转换的实体类对象
     * @return java.util.Map<java.lang.String, java.lang.Object> 包含实体类字段和值的 Map
     * @since  将实体类对象转换为 Map
     */
    public static Map<String, Object> convertEntityToMap(Object entity) {
        if (entity == null) {
            return new HashMap<>();
        }

        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = entity.getClass();

        // 循环处理所有字段（包括父类字段）
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    // 设置可访问私有字段
                    field.setAccessible(true);

                    // 获取字段值并放入 Map
                    Object value = field.get(entity);
                    map.put(field.getName(), value);
                } catch (IllegalAccessException e) {
                    // 处理异常（可根据需要记录日志）
                    System.err.println("Error accessing field: " + field.getName());
                }
            }
            // 获取父类继续处理
            clazz = clazz.getSuperclass();
        }

        return map;
    }

    public static <K, V> Map<K, V> convertEntityToMap(Object entity, Class<K> keyType, Class<V> valueType) {
        if (entity == null) {
            return new HashMap<>();
        }

        Map<K, V> map = new HashMap<>();
        Class<?> clazz = entity.getClass();

        // 循环处理所有字段（包括父类字段）
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    // 设置可访问私有字段
                    field.setAccessible(true);

                    // 获取字段值并放入 Map
                    Object value = field.get(entity);
                    map.put(keyType.cast(field.getName()), valueType.cast(value));
                } catch (IllegalAccessException e) {
                    // 处理异常（可根据需要记录日志）
                    System.err.println("Error accessing field: " + field.getName());
                }
            }
            // 获取父类继续处理
            clazz = clazz.getSuperclass();
        }

        return map;
    }

    /**
     * @param entity 要转换的实体类对象
     * @param fields 需要转换的属性
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since 将实体类对象转换为 Map,指定属性
     */
    public static Map<String, Object> convertEntityToMapWithFields(Object entity, List<String> fields) {
        if (entity == null) {
            return new HashMap<>();
        }

        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = entity.getClass();

        // 循环处理所有字段（包括父类字段）
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    // 设置可访问私有字段
                    field.setAccessible(true);

                    // 获取字段值并放入 Map
                    Object value = field.get(entity);
                    if (fields.contains(field.getName())) {
                        map.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    // 处理异常（可根据需要记录日志）
                    System.err.println("Error accessing field: " + field.getName());
                }
            }
            // 获取父类继续处理
            clazz = clazz.getSuperclass();
        }

        return map;
    }

    /**
     * @param entity 要转换的实体类对象
     * @param fields 需要转换的属性
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @since  将实体类对象转换为 Map,排除指定属性
     */
    public static Map<String, Object> convertEntityToMapWithoutFields(Object entity, List<String> fields) {
        if (entity == null) {
            return new HashMap<>();
        }

        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = entity.getClass();

        // 循环处理所有字段（包括父类字段）
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    // 设置可访问私有字段
                    field.setAccessible(true);

                    // 获取字段值并放入 Map
                    Object value = field.get(entity);
                    if (!fields.contains(field.getName())) {
                        map.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    // 处理异常（可根据需要记录日志）
                    System.err.println("Error accessing field: " + field.getName());
                }
            }
            // 获取父类继续处理
            clazz = clazz.getSuperclass();
        }

        return map;
    }


    /**
     * @param map         包含字段数据的 Map
     * @param entityClass 目标实体类的 Class 对象
     * @return T 填充好数据的实体类对象
     * @since 将 Map 转换回实体类对象
     */
    public static <T> T convertMapToEntity(Map<String, Object> map, Class<T> entityClass) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        try {
            // 创建实体类实例
            T entity = entityClass.getDeclaredConstructor().newInstance();
            Class<?> clazz = entity.getClass();

            // 循环处理所有字段（包括父类字段）
            while (clazz != null && clazz != Object.class) {
                for (Field field : clazz.getDeclaredFields()) {
                    String fieldName = field.getName();

                    // 检查 Map 中是否包含该字段
                    if (map.containsKey(fieldName)) {
                        try {
                            // 设置可访问私有字段
                            field.setAccessible(true);

                            // 获取字段类型和 Map 中的值
                            Object value = map.get(fieldName);
                            setFieldValue(entity, field, value);
                        } catch (IllegalAccessException e) {
                            System.err.println("Error setting field: " + fieldName);
                        }
                    }
                }
                // 获取父类继续处理
                clazz = clazz.getSuperclass();
            }

            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create entity instance", e);
        }
    }

    /**
     * @param map         包含字段数据的 Map
     * @param entityClass 目标实体类的 Class 对象
     * @param fields      需要转换的属性,必须是实体类存在的属性
     * @return T 填充好数据的实体类对象
     * @since 将 Map 转换回实体类对象,
     */
    public static <T, M> T convertMapToEntity(Map<String, Object> map, Class<T> entityClass, List<String> fields) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        try {
            // 创建实体类实例
            T entity = entityClass.getDeclaredConstructor().newInstance();
            Class<?> clazz = entity.getClass();

            // 循环处理所有字段（包括父类字段）
            while (clazz != null && clazz != Object.class) {
                for (Field field : clazz.getDeclaredFields()) {
                    String fieldName = field.getName();

                    // 检查 Map 中是否包含该字段
                    if (map.containsKey(fieldName)) {
                        try {
                            // 设置可访问私有字段
                            field.setAccessible(true);

                            // 获取字段类型和 Map 中的值
                            Object value = map.get(fieldName);
                            if (fields.contains(fieldName)) {
                                setFieldValue(entity, field, value);
                            }
                        } catch (IllegalAccessException e) {
                            System.err.println("Error setting field: " + fieldName);
                        }
                    }
                }
                // 获取父类继续处理
                clazz = clazz.getSuperclass();
            }

            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create entity instance", e);
        }
    }

    private static void setFieldValue(Object entity, Field field, Object value) throws IllegalAccessException {
        Class<?> fieldType = field.getType();

        // 类型转换处理
        if (value != null && !fieldType.isAssignableFrom(value.getClass())) {
            // 添加更多类型转换逻辑（根据需要扩展）
            // 添加更多类型转换逻辑（根据需要扩展）
            if ((fieldType == Long.class || fieldType == long.class)) {
                if (value instanceof Number) {
                    value = ((Number) value).longValue();
                } else if (value instanceof String) {
                    value = Long.parseLong((String) value);
                }
            } else if ((fieldType == Integer.class || fieldType == int.class)) {
                if (value instanceof Number) {
                    long l = ((Number) value).longValue(); // 统一用 long 判断，避免类型不匹配
                    if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
                        throw new IllegalArgumentException("值超出 Integer 范围: " + l);
                    }
                    value = (int) l;
                } else if (value instanceof String) {
                    value = Integer.parseInt((String) value);
                }
            } else if (fieldType == String.class) {
                value = value.toString();
            } else if (fieldType == Date.class) {
                System.out.println();
                value = new Date((Long) value);
            }
            // 添加其他类型转换...
        }

        // 设置字段值
        field.set(entity, value);
    }
}
