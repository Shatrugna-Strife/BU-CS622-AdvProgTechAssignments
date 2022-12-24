package com.met622;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/shell_script.sh";
        ProcessBuilder pb = new ProcessBuilder(path);
        try {
            Process p;
            p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line=br.readLine()) != null)
                System.out.println(line);
            int rc = p.waitFor();
            System.out.println("Process ended with exit code=" + rc);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}

