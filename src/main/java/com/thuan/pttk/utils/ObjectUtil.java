package com.thuan.pttk.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

    public static Map<String, Object> convertObjectToMap(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> fieldMap = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(object);
            fieldMap.put(field.getName(), value);
        }

        return fieldMap;
    }
}
