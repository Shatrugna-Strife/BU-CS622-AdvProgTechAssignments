package com.met622.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.met622.entity.Cache;
import com.met622.utility.FileOperations;
import com.met622.utility.SearchOperation;

import java.net.URISyntaxException;
import java.util.Date;

public abstract class Singleton {
    public static final FileOperations fileOperations;
    public static final SearchOperation searchOperation = new SearchOperation();
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final Date date = new Date();
    public static final Cache cache = new Cache();

    static {
        try {
            fileOperations = new FileOperations();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
