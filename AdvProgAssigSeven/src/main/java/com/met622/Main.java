package com.met622;

import java.util.Random;

public class Main {
    private char[] GENOME_SEQUENCE = new char[]{'A', 'T', 'G', 'C'};

//    private Random random = new Random();

    private Runnable thread = new Runnable() {
        @Override
        public void run() {

        }
    };

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.generateSequenceDnaString(10, 100, true);
        main.generateSequenceMultiThreaded(5, 10, 20, true);
        main.generateSequenceDnaString(100, 1000000,false);
        main.generateSequenceMultiThreaded(100, 100, 10000, false);
    }

    private void generateSequenceDnaString(int t, int n, boolean print){
        long startTime = System.currentTimeMillis();
        generateSequence(t,n, print);
        long endTime = System.currentTimeMillis();
        System.out.println("Total Execution to generate "+n+" sequences of length "+t+" time without threads: "+(endTime-startTime)+" milli sec\n\n\n");
    }

    private void generateSequenceMultiThreaded(int threads, int t, int n, boolean print) throws InterruptedException {
        Runnable runnableInstance = new Runnable() {
            @Override
            public void run() {
                generateSequence(t,n, print);
            }
        };
        Thread[] threadArray = new Thread[threads];
        for(int i =0;i<threads;i++){
            threadArray[i] = new Thread(runnableInstance);
        }
        long startTime = System.currentTimeMillis();
        for(int i =0;i<threads;i++){
            threadArray[i].start();
        }
        for(int i =0;i<threads;i++){
            threadArray[i].join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total Execution to generate "+n*threads+" sequences of length "+t+" time with "+threads+" threads: "+(endTime-startTime)+" milli sec\n\n\n");
    }

    public void generateSequence(int t, int n, boolean print){
        Random random = new Random();
        int k =0;
        while(k<n) {
            String word ="";
            for (int i = 0; i < t; i++) {
                int x = random.nextInt(4);
                word+=GENOME_SEQUENCE[x];
            }
//            System.out.println((k+1)+" : "+word);
            if(print)
            System.out.println(word);
            k++;
        }
    }

}

