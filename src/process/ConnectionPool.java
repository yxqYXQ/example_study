package process;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {

        if(initialSize>0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public Connection fetchConnection(long mils) throws InterruptedException {
        synchronized (pool){
            if(mils<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() +mils;
                long remaining = mils;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining = future -System.currentTimeMillis();
                }
            }
            Connection result = null;
            if(!pool.isEmpty()){
                pool.removeFirst();
            }
            return result;
        }
    }

    public void releaseConnection (Connection connection){
        if (connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notify();
            }
        }
    }
}
