package org.yameen.oday;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Service {
     public int[] count = new int[26];
    private   String AbsoluteFileDirectory;

    public String getAbsoluteFileDirectory() {
        return AbsoluteFileDirectory;
    }

    public void setAbsoluteFileDirectory(String absoluteFileDirectory) {
        AbsoluteFileDirectory = absoluteFileDirectory;
    }

    public  void displayDirectoryContents(String pt) throws IOException {
        ExecutorService pool= Executors.newFixedThreadPool(10);
        Files.walk(Paths.get(pt))
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(path -> {

                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Task #"+ Thread.currentThread().getId()+"  on file ==> "+path.toString());
                            try {
                                doCountOfASCII(path.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });







                });
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 26; i++) {
            System.out.print((char) (i + 'a'));
            System.out.println(": " + count[i]);
        }


    }

    ///////////////////////////////////////
    ///////////////////////////////////////
    public void doCountOfASCII(String path) throws IOException {
        BufferedReader input=new BufferedReader(new FileReader(path));

        int value;
        while ((value = input.read()) != -1) {
            char c=(char)value;

                if((c >='a') && (c<='z')) {
                    incrementCount(c -'a' );

            }
            /// change ends.
        }



    }///////end doCountOfASCII

    public synchronized void incrementCount(int index){
        count[index]++;
    }



    class Task implements Runnable{
        String pt;

        public Task(String pt) {
            this.pt = pt;
            System.out.println("Task #"+ Thread.currentThread().getId()+"  on file ==> "+pt);
        }

        @Override
        public void run() {
            try {
                doCountOfASCII(pt);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    }


