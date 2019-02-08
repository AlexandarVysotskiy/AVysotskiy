package manager;

import java.io.File;

public interface ServerInt {
    void start(String startPath);

    File getListFilesInDirectory(int number, File file);

    void download(File src, File dest);
}
