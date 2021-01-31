package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-06-29 16:40
 **/
public class WriteAndReadTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:\\employee.txt");
        FileOutputStream fos = new FileOutputStream("D:\\employee11111.txt");
        FileChannel inputChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(512);

        while (true) {
            buf.clear();
            int read = inputChannel.read(buf);
            System.out.println(read);
            if (read == -1) {
                break;
            }
            buf.flip();
            outChannel.write(buf);
        }
        inputChannel.close();
        outChannel.close();
    }
}
