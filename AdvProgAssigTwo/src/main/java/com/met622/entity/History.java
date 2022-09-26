package com.met622.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.met622.singleton.Singleton.date;

public class History {

    private Map<String, List<Long>> commandHistory;

    public History(){
        commandHistory = new HashMap<>(30);
    }

    public void addCommand(String command){
         if(!commandHistory.containsKey(command)){
             commandHistory.put(command, new ArrayList<Long>(){{add(date.getTime());}});
         }else{
             commandHistory.get(command).add(date.getTime());
         }
    }
}
