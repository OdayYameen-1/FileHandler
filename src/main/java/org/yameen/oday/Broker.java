package org.yameen.oday;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker
{
    public BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(1500);
    public Boolean continueProducing = Boolean.TRUE;

    public void put(Runnable data) throws InterruptedException
    {
        this.queue.put(data);
        System.err.println("pppppppppppppppppppppppppppppppppppppppppp"+this.queue.size());
    }

    public Runnable get() throws InterruptedException
    {
        System.err.println("ccccccccccccccccccccccccccccccccccccccccccc"+this.queue.size());
        return this.queue.take();

    }
}