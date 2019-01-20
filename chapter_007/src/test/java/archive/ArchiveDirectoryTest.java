package archive;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArchiveDirectoryTest {
    private List<String> listExt;
    private ArchiveDirectory archiveDirectory = new ArchiveDirectory();
    private String pathSrs;
    private String pathDest;
    private File directory;

    @Test
    public void whenFindFileThenTrue() {
        directory = new File(System.getProperty("java.io.tmpdir") + "zipFolderTest");
        directory.mkdir();
        pathSrs = System.getProperty("java.io.tmpdir") + "testFolderForJava";
        pathDest = directory.toString() + "\\testZip.zip";
        listExt = new ArrayList<>();
        listExt.add("txt");
        listExt.add("exe");
        archiveDirectory.archive(listExt, pathSrs, pathDest);
        assertTrue(new File(directory + "\\testZip.zip").exists());
    }

    @Test
    public void whenFindFileThenFalse() {
        directory = new File(System.getProperty("java.io.tmpdir") + "zipFolderTest");
        directory.mkdir();
        pathSrs = System.getProperty("java.io.tmpdir") + "testFolderForJava";
        pathDest = directory.toString() + "\\testZipTwo.zip";
        listExt = new ArrayList<>();
        listExt.add("txt");
        listExt.add("exe");
//        archiveDirectory.archive(listExt,pathSrs, pathDest);
        assertFalse(new File(directory + "\\testZipTwo.zip").exists());
    }
}