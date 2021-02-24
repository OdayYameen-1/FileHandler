package org.yameen.oday;

import java.util.concurrent.*;

public class Consumer extends Thread
{
    ExecutorService pool= Executors.newFixedThreadPool(5);

    private Broker broker;
    Future prodstatus=null;


    public Consumer( Broker broker)
    {
        this.broker = broker;
    }


    @Override
    public void run()
    {
        try
        {


            while (broker.continueProducing ||broker.queue.size()>0)
            {
                Runnable data = broker.get();
                System.out.println("Consumer   processed data from broker: "+data.toString() );


           prodstatus= pool.submit(data);

            }

            System.out.println("Comsumer  finished its job; terminating.");
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

}
