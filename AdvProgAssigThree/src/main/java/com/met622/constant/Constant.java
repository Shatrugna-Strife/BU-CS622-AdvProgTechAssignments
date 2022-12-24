package com.met622.constant;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores constants
 */
public interface Constant {
    URI INDEX_DIR = new File(System.getProperty("user.dir")+ File.separator + "data").toURI();
    String XML_FILE_NAME = "dblp.xml";

    List<String> WORD_LIST = new ArrayList<String>(){{add("Conversational Agent");add("Chatbot");add("Personal Assistant");add("Smart Speaker");}};
}
