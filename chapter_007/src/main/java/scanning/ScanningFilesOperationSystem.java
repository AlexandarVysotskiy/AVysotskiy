package scanning;

import java.io.File;
import java.util.*;

/**
 * Class ScanningFilesOperationSystem
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since 17.01.2019
 */

public class ScanningFilesOperationSystem {

    /**
     * @param parent    The find starts in this catalog;
     * @param extension The list of the extension;
     * @return List of files with specific extension;
     */

    public List<File> files(String parent, List<String> extension) {
        Iterable<File> fileList = getFileAsList(parent);
        ArrayList<File> result = new ArrayList<>();
        String fileExtension;
        for (File file : fileList) {
            fileExtension = file.getName().substring(file.getName().indexOf(".") + 1);
            if (extension.contains(fileExtension)) {
                result.add(file);
            }
        }
        return result;
    }

    /**
     * @param parent The find starts in this catalog;
     * @return List of the address with files.
     */
    private Iterable<File> getFileAsList(String parent) {
        ArrayList<File> result = new ArrayList<>();
        Deque<File> filesList = new ArrayDeque<>();
        filesList.offer(new File(parent));
        File temp;
        while (!filesList.isEmpty()) {
            temp = filesList.pollFirst();
            if (temp.isDirectory()) {
                filesList.addAll(Arrays.asList(temp.listFiles()));
            } else {
                result.add(temp);
            }
        }
        return result;
    }
}
