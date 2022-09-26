package com.met622.utility;

import com.met622.model.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.met622.singleton.Singleton.objectMapper;
import static com.met622.singleton.Singleton.cache;


public class SearchOperation {
    public List<Map<String,Object>> searchJsonFileByKeyword(File file, String keyword) throws IOException {
        BufferedReader input = null;
        List<Map<String,Object>> result = new ArrayList<>(20);
        try{
            input = new BufferedReader(new FileReader(file));
            String st;
            StringBuilder sb;
            if(cache.getCacheData().size()!=0){
                for(Pair<String,Map<String,Object>> p:cache.getCacheData()){
                    if(p.getFirst().contains(keyword.toLowerCase())){
                        result.add(p.getSecond());
                    }
                }
                return result;
            }
            while((st = input.readLine())!=null){
                sb = new StringBuilder();
                Map<String,Object> tmpMap = objectMapper.readValue(st,Map.class);
                buildStringData(tmpMap, sb);
                if(sb.toString().toLowerCase().contains(keyword.toLowerCase())){
                    result.add(extractJSON(tmpMap));
                }
                cache.insertCacheValue(new Pair<>(sb.toString().toLowerCase(),extractJSON(tmpMap)));
            }
            return result;
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            input.close();
        }
    }

    private Map<String, Object> extractJSON(Map<String, Object> tmpMap) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> temp = (Map)tmpMap.get("data");
        result.put("project_name",(String)temp.get("name"));
        result.put("funding_amount",(String)temp.get("pledged"));
        result.put("goal_amount",(String)temp.get("goal"));
        temp = (Map)temp.get("category");
        result.put("category",(String)temp.get("parent_name"));
        return result;
    }

    private void buildStringData(Map<String, Object> tmpMap, StringBuilder sb) {
        Map<String,Object> temp = (Map)tmpMap.get("data");
        sb.append((String)temp.get("name"));
        sb.append((String)temp.get("blurb"));
        sb.append((String)temp.get("slug"));
        temp = (Map)temp.get("category");
        sb.append((String)temp.get("name"));
        sb.append((String)temp.get("analytics_name"));
        sb.append((String)temp.get("slug"));
        sb.append((String) temp.get("parent_name"));
    }
}
