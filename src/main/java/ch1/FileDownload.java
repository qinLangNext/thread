package ch1;

import util.Debug;
import util.Tools;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-06-18 18:37
 **/
public class FileDownload {
    public static void main(String[] args) {
        String[] a = {"http://static.mokucloud.com/public/tcms/res/fuli/new_user_welfare_coin.png",
                "http://static.mokucloud.com/public/tcms/res/fuli/daily_video2.png"};
        Thread t =null;
        for (int i=0; i<a.length; i++){
            t = new Thread(new FileDownloader(a[i]));
            t.start();
        }
    }

    static class FileDownloader implements Runnable {
        private final String fileURL;

        public FileDownloader(String fileURL) {
            this.fileURL = fileURL;
        }

        @Override
        public void run() {
            String fileName = this.fileURL.substring(this.fileURL.lastIndexOf("/") + 1);
            try {
                URL url = new URL(this.fileURL);
                String localFileName = "D:\\moku\\thread\\" + fileName;
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private  void downloadFile(URL url, OutputStream outputStream, int bufSize) throws IOException {
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            ReadableByteChannel inChannel = null;
            WritableByteChannel outChannel = null;
            try {
                int code = conn.getResponseCode();
                if (2 != code / 100) {
                    throw new IOException("Error: HTTP " + code);
                }
                if (0 == conn.getContentLength()) {
                    Debug.info("空文件！！！！");
                    return;
                }
                inChannel = Channels.newChannel(new BufferedInputStream(conn.getInputStream()));
                outChannel = Channels.newChannel(new BufferedOutputStream(outputStream));
                ByteBuffer buf = ByteBuffer.allocate(bufSize);
                while (-1 != inChannel.read(buf)){
                    buf.flip();
                    outChannel.write(buf);
                    buf.clear();
                }
            } finally {
                Tools.silentClose(inChannel, outChannel);
            }

        }
    }


}
