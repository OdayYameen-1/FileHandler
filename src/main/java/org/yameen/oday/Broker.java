package org.yameen.oday;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker
{
    public ArrayBlockingQueue<Thread> queue = new ArrayBlockingQueue<Thread>(10);
    public Boolean continueProducing = Boolean.TRUE;

    public void put(Thread data) throws InterruptedException
    {
        this.queue.put(data);
        System.err.println("The Thread id ==>  "+data.getId());
    }

    public Thread get() throws InterruptedException
    {
        return this.queue.take();
    }
}