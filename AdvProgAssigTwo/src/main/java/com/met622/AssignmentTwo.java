package com.met622;

import com.met622.utility.FileOperations;
import static com.met622.singleton.Singleton.fileOperations;
import static com.met622.singleton.Singleton.searchOperation;

import java.io.File;
import java.net.URISyntaxException;

public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("asfasf");
        try {
            searchOperation.searchJsonFileByKeyword(new File(fileOperations.getDataFolderFile().getAbsoluteFile() + File.separator+"mergedFile.json"),  "robot");
//            fileOperations.mergeFilesInDataFolder("mergedFile.json");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
//        File[] ba = new File("D:\\Projects\\BU-CS622-AdvProgTechAssignments\\AdvProgAssigTwo\\data").listFiles();
//        System.out.println("hguj");
    }
}