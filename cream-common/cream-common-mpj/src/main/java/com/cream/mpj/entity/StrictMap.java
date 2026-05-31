package com.cream.mpj.entity;

import org.apache.ibatis.binding.BindingException;

import java.io.Serial;
import java.util.HashMap;

/**
 * 严格模式的 HashMap
 *
 * @author Cream
 * @since 2026-05-31 21:03
 */
public class StrictMap<V> extends HashMap<String, V> {

    @Serial
    private static final long serialVersionUID = -5741767162221585340L;

    @Override
    public V get(Object key) {
        if (!super.containsKey(key)) {
            throw new BindingException("Parameter '" + key + "' not found. Available parameters are " + this.keySet());
        }
        return super.get(key);
    }

}
