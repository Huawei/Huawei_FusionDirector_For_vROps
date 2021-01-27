package com.huawei.adapter.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.integrien.alive.common.adapter3.ResourceKey;
import com.integrien.alive.common.adapter3.config.ResourceIdentifierConfig;

public class ResourceKeyCache {
    
    private static final Map<String,String> CHANGED_KEYS = new java.util.concurrent.ConcurrentHashMap<>();
    
    private static final Map<String,String> CACHE = new java.util.concurrent.ConcurrentHashMap<>();
    
    private static final Map<String,String> KEY_EXIST_MAP = new java.util.concurrent.ConcurrentHashMap<>();
    
    private static final Map<String,ResourceKey> RESOURCE_KEY_MAP = new java.util.concurrent.ConcurrentHashMap<>();
    
    public static void add(String identifier, String label, ResourceKey resourceKey){
        
        if (CHANGED_KEYS.containsKey(identifier)) {
            //check if label changed
            String oldLabel = CACHE.get(identifier);
            if (oldLabel.equals(label) == false) {
                CHANGED_KEYS.put(identifier, label);
            }
        } 
        
        if (label != null) {
        	CACHE.put(identifier, label);
        }
        
        KEY_EXIST_MAP.put(identifier, "Y");
        RESOURCE_KEY_MAP.put(identifier, resourceKey);
        
    }
    
    public static void resetExistFlag(String prefix){
        for (Entry<String,String> entry : KEY_EXIST_MAP.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                KEY_EXIST_MAP.put(entry.getKey(), "N");
            }
        }
    }
    
    public static int getChangedKeySize(){
        return CHANGED_KEYS.size();
    }
    
    public static boolean checkKeyLabelChanged(ResourceKey key) {

        for (ResourceIdentifierConfig cfg : key.getIdentifiers()) {
            if (CHANGED_KEYS.containsKey(cfg.getValue())) {
                CHANGED_KEYS.remove(cfg.getValue());
                return true;
            }
        }

        return false;
    }

    /**
     * 查询删除的resourceKey
     *
     * @param prefix key的前缀
     * @return 被删除的key的列表
     */
    public static List<ResourceKey> getRemovedKeys(String prefix) {
        List<ResourceKey> removeList = new ArrayList<>();

        Iterator<Entry<String, String>> it = KEY_EXIST_MAP.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if ("N".equals(entry.getValue()) && entry.getKey().startsWith(prefix)) {
                removeList.add(RESOURCE_KEY_MAP.get(entry.getKey()));
                RESOURCE_KEY_MAP.remove(entry.getKey());
                it.remove();
            }
        }
        return removeList;
    }
}
