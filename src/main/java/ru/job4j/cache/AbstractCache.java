package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        V strongRefValue = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (strongRefValue == null) {
            strongRefValue = load(key);
            put(key, strongRefValue);
        }
        return strongRefValue;
    }

    protected abstract V load(K key);

}