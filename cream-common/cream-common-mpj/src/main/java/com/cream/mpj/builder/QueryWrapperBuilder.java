package com.cream.mpj.builder;

import com.cream.mpj.annotation.QueryType;
import com.cream.mpj.enums.QueryTypeEnum;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

/**
 * 查询条件Wrapper构造器
 *
 * @author Cream
 * @since 2026-06-09 22:54
 */
@NoArgsConstructor
public class QueryWrapperBuilder {

    public static <T> void build(MPJLambdaWrapper<T> wrapper, Object obj, String aliasName) {
        try {
            Class<?> c = obj.getClass();
            //获取类属性
            Field[] selfFields = c.getDeclaredFields();
            //获取父类属性
            Field[] subFields = c.getSuperclass().getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(selfFields, subFields);
            int fieldsLength = fields.length;

            for (Field field : fields) {
                field.setAccessible(true);
                QueryType annotation = field.getAnnotation(QueryType.class);
                Object filedObj = field.get(obj);
                if (Objects.isNull(annotation) || Objects.isNull(filedObj)) {
                    continue;
                }
                String filedName = aliasName + "." + annotation.filedName();
                QueryTypeEnum type = annotation.value();
                if (!StringUtils.isBlank(filedName)) {
                    if (isCollection(filedObj)) {
                        Collection<?> collectionField = (Collection<?>) filedObj;
                        if (!CollectionUtils.isEmpty(collectionField)) {
                            if (type.equals(QueryTypeEnum.IN)) {
                                wrapper.in(filedName, collectionField);
                            }
                        }
                    } else {
                        if (!StringUtils.isEmpty(filedObj.toString())) {
                            if (type.equals(QueryTypeEnum.EQ)) {
                                wrapper.eq(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.NE)) {
                                wrapper.ne(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.LIKE)) {
                                wrapper.like(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.LIKE_COMMA)) {
                                wrapper.like(filedName, "," + filedObj + ",");
                            } else if (type.equals(QueryTypeEnum.GT)) {
                                wrapper.gt(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.LT)) {
                                wrapper.lt(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.GTE)) {
                                wrapper.ge(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.LTE)) {
                                wrapper.le(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.LEFT_LIKE)) {
                                wrapper.likeLeft(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.RIGHT_LIKE)) {
                                wrapper.likeRight(filedName, filedObj);
                            } else if (type.equals(QueryTypeEnum.ORDER_BY_COLUMN_DESC)) {
                                wrapper.orderByDesc(filedName);
                            } else if (type.equals(QueryTypeEnum.ORDER_BY_COLUMN_ASC)) {
                                wrapper.orderByAsc(filedName);
                            }
                        }
                    }
                }
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }
    }

    /**
     * 判断是否集合
     * @param obj 需要判断的对象
     */
    private static Boolean isCollection(Object obj) {
        //判断是否为集合
        return obj instanceof Collection;
    }

}
