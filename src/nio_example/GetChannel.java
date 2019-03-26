package nio_example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static final int BSIZE  = 1024;
    public static void main(String args[]) throws Exception {
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();
//        fc = new RandomAccessFile("data.txt","rw").getChannel();
//        fc.position(fc.position());
//        fc.write(ByteBuffer.wrap("some type".getBytes()));
//        fc.close();
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
//        while(buff.hasRemaining()){
//            System.out.println((char)buff.get());
//        }
        System.out.println(buff.asCharBuffer());
        while(fc.read(buff)!=-1){

        }
    }
}
