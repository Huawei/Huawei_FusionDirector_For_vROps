package com.huawei.fd.util;

import java.util.ArrayList;
import java.util.List;

public class StaticToolkit<K, V> {
    
    private List<Entry<K, V>> entries = new ArrayList<>();

    public void add(K k, V v) {
        entries.add(new Entry<>(k, v));
    }

    public List<K> getKeysByValue(V val) {
        List<K> list = new ArrayList<>();
        for (Entry<K, V> entry : entries) {
            if (entry.getValue().equals(val)) {
                list.add(entry.getKey());
            }
        }

        return list;
    }
}

class Entry<K, V> {
    
    private K k;
    
    private V v;

    public Entry(K k, V v) {
        super();
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }
}
