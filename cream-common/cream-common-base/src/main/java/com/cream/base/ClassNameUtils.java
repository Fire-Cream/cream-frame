package com.cream.base;

/**
 * 类名工具类
 *
 * @author Cream
 * @since 2026-06-09 23:10
 */
public class ClassNameUtils {

    /**
     * 获取类名的字母简写（首字母小写）
     * 例如：DataInfo -> di，Student -> s，UserDTO -> udto
     *
     * @param clazz 要获取简写的类
     * @return 类名的字母简写
     */
    public static String getClassAbbreviation(Class<?> clazz) {
        if (clazz == null) {
            return "";
        }

        String className = clazz.getSimpleName();
        if (className.isEmpty()) {
            return "";
        }

        StringBuilder abbreviation = new StringBuilder();
        // 添加第一个个字符的小写形式
        abbreviation.append(Character.toLowerCase(className.charAt(0)));

        // 从第2个字符开始，遇到大写字母的首字母
        for (int i = 1; i < className.length(); i++) {
            char c = className.charAt(i);
            if (Character.isUpperCase(c)) {
                abbreviation.append(Character.toLowerCase(c));
            }
        }

        return abbreviation.toString();
    }

}

