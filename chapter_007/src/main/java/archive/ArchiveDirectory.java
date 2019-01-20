package archive;

import scanning.ScanningFilesOperationSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class ArchiveDirectory
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since 20.01.18
 */
public class ArchiveDirectory {

    private ScanningFilesOperationSystem scan = new ScanningFilesOperationSystem();

    /**
     * This method create archive of files;
     *
     * @param extension extension of files, witch are added in archive;
     * @param scr       way directory with files for add.
     * @param dest      way of directory into which are created zip archive and name future zip file.
     */
    public void archive(List<String> extension, String scr, String dest) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(dest))) {
            for (File f : scan.files(scr, extension)) {
                zip.putNextEntry(new ZipEntry(f.getAbsolutePath().substring(scr.length())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}