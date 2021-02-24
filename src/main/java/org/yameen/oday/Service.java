package org.yameen.oday;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Service {
     public int[] count = new int[26];
    private   String AbsoluteFileDirectory;
    Broker broker=new Broker();
    Consumer consumer=new Consumer(broker);

    public String getAbsoluteFileDirectory() {
        return AbsoluteFileDirectory;
    }

    public void setAbsoluteFileDirectory(String absoluteFileDirectory) {
        AbsoluteFileDirectory = absoluteFileDirectory;
    }

    public  void displayDirectoryContents(String pt) throws IOException {



        consumer.start();


        Files.walk(Paths.get(pt))
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(path -> {


        try {
            System.out.println("Producer produced: " + path.toString());
            Thread.sleep(100);
            broker.put(new Runnable(){

                @Override
                public void run(){

                    System.out.println("Task #"+ Thread.currentThread().getId()+"  on file ==> "+path.toString());
                    try {
                        doCountOfASCII(path.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }



            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






                });///////////end for each
        this.broker.continueProducing = Boolean.FALSE;

        System.out.println("Producer finished its job; terminating.");

        for (int i = 0; i < 26; i++) {
            System.out.print((char) (i + 'a'));
            System.out.println(": " + count[i]);
        }


    }




    ///////////////////////////////////////
    ///////////////////////////////////////
    public void doCountOfASCII(String path) throws IOException {
        BufferedReader input=new BufferedReader(new FileReader(path));
        int fackCount[]=new int[26];
        int value;
        while ((value = input.read()) != -1) {
            char c=(char)value;

                if((c >='a') && (c<='z')) {
                    fackCount[c -'a' ]++;

            }
            /// change ends.
        }


        incrementCount(fackCount);
    }///////end doCountOfASCII

    public synchronized void incrementCount(int[] fackCount){
        for(int i=0;i<26;i++){
            count[i]=count[i]+fackCount[i];


        }


    }




    }


