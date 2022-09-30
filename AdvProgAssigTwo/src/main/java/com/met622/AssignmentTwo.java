package com.met622;

import com.met622.entity.SwingGui;
import com.met622.utility.FileOperations;
import static com.met622.singleton.Singleton.fileOperations;
import static com.met622.singleton.Singleton.searchOperation;

import java.io.File;
import java.net.URISyntaxException;

public class AssignmentTwo {
    public static void main(String[] args) {
        new SwingGui();
    }
}