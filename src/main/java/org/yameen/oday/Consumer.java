package org.yameen.oday;

import java.util.concurrent.*;

public class Consumer extends Thread
{
    ExecutorService pool= Executors.newFixedThreadPool(10);

    private Broker broker;
    Future prodstatus=null;
   Runnable data=null;

    public Consumer( Broker broker)
    {
        this.broker = broker;
    }


    @Override
    public void run()
    {
        try
        {


            while ((broker.continueProducing ||broker.queue.size()>0))
            {
                data=broker.queue.take();
                if(data!=null) {
                    System.out.println("Consumer   processed data from broker: ");


                    prodstatus = pool.submit(data);
                }
            }

            System.out.println("Comsumer  finished its job; terminating.");

        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

}
