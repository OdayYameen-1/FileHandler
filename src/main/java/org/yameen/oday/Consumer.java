package org.yameen.oday;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread
{


    private Broker broker;


    public Consumer( Broker broker)
    {
        this.broker = broker;
    }


    @Override
    public void run()
    {
        try
        {
            Thread data = broker.get();

            while (broker.continueProducing || data != null)
            {
                Thread.sleep(100);
                System.out.println("Consumer   processed data from broker: "+data.getId() );

                data = broker.get();
                data.start();

            }


            System.out.println("Comsumer  finished its job; terminating.");
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

}
