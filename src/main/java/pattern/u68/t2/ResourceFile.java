package pattern.u68.t2;

/**
 * @program: thread
 * @author: lang.qin
 * @create: 2020-10-29 18:28
 **/
public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    abstract public void accept(Extractor extractor);
    abstract public void accept(Compressor compressor);
}
