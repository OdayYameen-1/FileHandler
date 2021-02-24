package org.yameen.oday;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread{
    protected BlockingQueue<Runnable> sharedQueue;
    ExecutorService pool= Executors.newFixedThreadPool(10);
    private Runnable threadtoGet;
    Consumer(BlockingQueue<Runnable> theQueue) {
        super("Consumer");
        this.sharedQueue = theQueue;
    }

    public Runnable getThreadtoGet() {
        return threadtoGet;
    }

    public void setThreadtoGet(Runnable threadtoGet) {
        this.threadtoGet = threadtoGet;
    }

    @Override
    public void run() {
        try
        {
            while (true)
            {
                Runnable objThread = sharedQueue.take();
                System.out.println("Consumed resource - Queue size now = "  + sharedQueue.size());
                Thread.sleep(100);
                pool.submit(objThread);

            }

                pool.shutdown();
            pool.awaitTermination(20, TimeUnit.MINUTES);
        }
        catch (InterruptedException ex)
        {
            System.out.println("CONSUMER INTERRUPTED");
        }

    }
}
