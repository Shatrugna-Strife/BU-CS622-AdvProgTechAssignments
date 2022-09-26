package com.met622.utility;

import java.io.*;
import java.net.URISyntaxException;

public class FileOperations {

    public File getDataFolderFile() {
        return dataFolderFile;
    }

    private final File dataFolderFile;

    public FileOperations() throws URISyntaxException {
        dataFolderFile = getDataFolderFileFactory();
    }
    public File[] getFilesFromDataFolder() throws URISyntaxException {
        return dataFolderFile.listFiles();
    }

    private File getCurrentJarFile() throws URISyntaxException {
        return new File(FileOperations.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI());
    }

    private File getDataFolderFileFactory() throws URISyntaxException {
        File jarFile = getCurrentJarFile();
        return new File(jarFile.getParentFile().getParentFile().getParentFile().getParentFile().getAbsoluteFile() + File.separator + "data");
    }

    public File mergeFilesInDataFolder(String fileName) throws URISyntaxException, IOException {
        File[] filesToMerge = getFilesFromDataFolder();
        deleteOutputFileIfExits(filesToMerge, fileName);
        File outputFile = new File(dataFolderFile.getAbsoluteFile() + File.separator+fileName);
        BufferedWriter outputWriter = null;
        BufferedReader inputReader = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
            for (int i = 0; i < filesToMerge.length; i++) {
                inputReader = new BufferedReader(new FileReader(filesToMerge[i]));
                String st;
                while (true) {

                    if (!((st = inputReader.readLine()) != null)) break;

                    outputWriter.write(st);
                    outputWriter.write("\n");
                }
                inputReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            outputWriter.close();
            inputReader.close();
        }
        return outputFile;
    }

    private void deleteOutputFileIfExits(File[] files,String fileName){
        for(int i =0;i<files.length;i++) {
            if (files[i].getName().equals(fileName)) {
                files[i].delete();
            }
        }
    }
}
