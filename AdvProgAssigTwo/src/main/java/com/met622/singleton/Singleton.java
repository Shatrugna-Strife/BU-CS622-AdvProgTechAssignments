package com.met622.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.met622.entity.Cache;
import com.met622.entity.History;
import com.met622.utility.FileOperations;
import com.met622.utility.PrintUtility;
import com.met622.utility.SearchOperation;

import java.net.URISyntaxException;
import java.util.Date;

/**
 * Class to mimic the singleton container inject mechanism
 */
public abstract class Singleton {
    public static final FileOperations fileOperations;
    public static final SearchOperation searchOperation = new SearchOperation();
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final Date date = new Date();
    public static final Cache cache = new Cache();
    public static final History history = new History();
    public static final PrintUtility printUtility = new PrintUtility();

    static {
        try {
            fileOperations = new FileOperations();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
