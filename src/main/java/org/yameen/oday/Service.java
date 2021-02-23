package org.yameen.oday;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Service {
    private List<String> filesPath=new ArrayList<>();
     public int[] count = new int[26];
    private   String AbsoluteFileDirectory;

    public String getAbsoluteFileDirectory() {
        return AbsoluteFileDirectory;
    }

    public void setAbsoluteFileDirectory(String absoluteFileDirectory) {
        AbsoluteFileDirectory = absoluteFileDirectory;
    }

    public  void displayDirectoryContents(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()&&!file.getName().startsWith("System")) {

                displayDirectoryContents(file);
            } else if(file.isFile()&&file.getName().endsWith(".txt")){
                filesPath.add(file.getAbsolutePath());
            }
        }



    }
    public void dotheMainTask(){
        displayDirectoryContents(new File(getAbsoluteFileDirectory()));

       int filepathLength=filesPath.size();
        System.err.println(filepathLength);;
        ExecutorService pool= Executors.newFixedThreadPool(10);


        for(String pt:filesPath) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task #"+ Thread.currentThread().getId()+"  on file ==> "+pt);
                doCountOfASCII(pt);
            }
        });


        }

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
    public void doCountOfASCII(String path){
        Scanner input = null;
        try {
            input = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (input.hasNextLine()) {
            String answer = input.nextLine();

            char[] characters = answer.toCharArray();
            /// change here!
            for (int i = 0; i< characters.length ; i++) {
                if((characters[i] >='a') && (characters[i]<='z')) {
                    incrementCount(characters[i] -'a' );
                }
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
            doCountOfASCII(pt);

        }
    }

    }


