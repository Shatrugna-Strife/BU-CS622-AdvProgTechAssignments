package com.met622.entity;

import com.met622.model.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * It is used to store the search results and avoid unnecessary IO read.
 */
public class Cache {
    public List<Pair<String, Map<String, Object>>> getCacheData() {
        return cacheData;
    }

    private List<Pair<String, Map<String,Object>>> cacheData;

    public Cache(){
        cacheData = new ArrayList<>();
    }

    public void insertCacheValue(Pair<String, Map<String, Object>> pair){
        cacheData.add(pair);
    }
}
