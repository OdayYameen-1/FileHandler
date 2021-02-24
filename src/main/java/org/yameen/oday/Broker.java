package org.yameen.oday;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker
{
    public ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
    public Boolean continueProducing = Boolean.TRUE;

    public void put(Runnable data) throws InterruptedException
    {
        this.queue.put(data);
        System.err.println("The Thread id ==>  "+data.toString());
    }

    public Runnable get() throws InterruptedException
    {
        return this.queue.take();
    }
}