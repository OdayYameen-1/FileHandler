package org.yameen.oday;

import java.util.concurrent.*;

public class Consumer extends Thread
{
    ExecutorService pool= Executors.newFixedThreadPool(10);

    private Broker broker;
    private Future prodStatus;


    public Consumer( Broker broker)
    {
        this.broker = broker;
    }


    @Override
    public void run()
    {
        try
        {


            while (broker.continueProducing )
            { Runnable data = broker.get();
                Thread.sleep(100);
                System.out.println("Consumer   processed data from broker: "+data.toString() );


               prodStatus= pool.submit(data);

            }

            pool.shutdown();
           pool.awaitTermination(20,TimeUnit.MINUTES);
            System.out.println("Comsumer  finished its job; terminating.");
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

}
