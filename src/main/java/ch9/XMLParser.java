package ch9;

import org.w3c.dom.Document;
import util.Debug;
import util.Tools;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.concurrent.*;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-06-30 17:29
 **/
public class XMLParser {


    public abstract static class ResultHandler {
        abstract void onSuccess(Document document);

        void onError(Throwable e) {
            e.printStackTrace();
        }
    }

    public static class ParsingTask {
        private final InputStream in;
        private volatile Executor executor;
        private volatile ResultHandler resultHandler;

        public ParsingTask(InputStream in, Executor executor, ResultHandler resultHandler) {
            this.in = in;
            this.executor = executor;
            this.resultHandler = resultHandler;
        }

        public Future<Document> execute() {
            FutureTask<Document> ft;
            final Callable<Document> task = new Callable<Document>() {
                @Override
                public Document call() throws Exception {
                    return doParse(in);
                }
            };
            final Executor theExecutor = executor;
            final boolean isAsyncParsing = null != theExecutor;
            final ResultHandler rh;
            if (isAsyncParsing && null != (rh = resultHandler)) {
                ft = new FutureTask<Document>(task){
                    @Override
                    protected void done() {
                       callbackResultHandler(this, rh);
                    }
                };
            }else {
                ft = new FutureTask<Document>(task);
            }
            if (isAsyncParsing){
                theExecutor.execute(ft);
            }else {
                ft.run();
            }
            return ft;
        }

        void callbackResultHandler(FutureTask<Document> ft, ResultHandler rh) {
            if (ft.isCancelled()) {
                Debug.info("");
                return;
            }
            try {
                Document doc = ft.get();
                rh.onSuccess(doc);
            } catch (InterruptedException e) {
                Debug.info("");
            } catch (ExecutionException e) {
                rh.onError(e.getCause());
            }
        }

        static Document doParse(InputStream in) throws Exception {
            Document document = null;
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
                document = db.parse(in);
            } finally {
                Tools.silentClose(in);
            }
            return document;
        }


        public ParsingTask(InputStream in) {
            this(in, null, null);
        }

        public ParsingTask setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public ParsingTask setResultHandler(ResultHandler resultHandler) {
            this.resultHandler = resultHandler;
            return this;
        }
    }
}
