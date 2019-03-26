package concurrency.atomic;

import java.util.concurrent.ThreadFactory;

public class DaemonHthreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread();
        t.setDaemon(true);
        return t;
    }
}
