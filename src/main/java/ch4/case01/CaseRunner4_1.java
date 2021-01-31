package ch4.case01;


import ch8.TPBigFileDownloader;
import util.Debug;

public class CaseRunner4_1 {

  public static void main(String[] args) throws Exception {
    if (0 == args.length) {
      args = new String[] { "http://static.mokucloud.com/download/qudao/2020/6/4/maoshu-4.0.0-310-maoshuShare-release-0604formal.apk", "2", "3" };
    }
    // TPBigFileDownloader.main(args);
    main0(args);
  }

  public static void main0(String[] args) throws Exception {
    final int argc = args.length;
    BigFileDownloader downloader = new BigFileDownloader(args[0]);


    // 下载线程数
    int workerThreadsCount = argc >= 2 ? Integer.valueOf(args[1]) : 2;
    long reportInterval = argc >= 3 ? Integer.valueOf(args[2]) : 2;

    Debug.info("downloading %s%nConfig:worker threads:%s,reportInterval:%s s.",
        args[0], workerThreadsCount, reportInterval);

    downloader.download(workerThreadsCount, reportInterval * 1000);
  }
}
