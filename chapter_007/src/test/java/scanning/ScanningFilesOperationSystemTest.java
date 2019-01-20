package scanning;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScanningFilesOperationSystemTest {
    ScanningFilesOperationSystem scanning = new ScanningFilesOperationSystem();
    private List<String> listExt;
    private String path;
    private File[] expected;
    private File[] directory;

    @Before
    public void setUp() throws IOException {
        path = System.getProperty("java.io.tmpdir");
        listExt = new ArrayList<>();
        listExt.add("txt");
        listExt.add("exe");

        directory = new File[]{new File(path + "testFolderForJava/testDirectory1/"),
                new File(path + "testFolderForJava/testDirectory2/oneLevel/twoLevel/"),
                new File(path + "testFolderForJava/testDirectory3/oneLevel/twoLevel/"),
                new File(path + "testFolderForJava/testDirectory4/oneLevel/"),
                new File(path + "testFolderForJava/testDirectory5/oneLevel/twoLevel/"),
                new File(path + "testFolderForJava/testDirectory6/oneLevel/twoLevel/threeLevel")};

        expected = new File[]{new File(path + "testFolderForJava/testDirectory1/file.txt"),
                new File(path + "testFolderForJava/testDirectory2/file1234.exe"),
                new File(path + "testFolderForJava/testDirectory2/oneLevel/file123.exe"),
                new File(path + "testFolderForJava/testDirectory4/oneLevel/fileOne.exe"),
                new File(path + "testFolderForJava/testDirectory4/oneLevel/fileTwo.exe"),
                new File(path + "testFolderForJava/testDirectory2/oneLevel/twoLevel/file.exe"),
                new File(path + "testFolderForJava/testDirectory3/oneLevel/twoLevel/file.txt"),
                new File(path + "testFolderForJava/testDirectory5/oneLevel/twoLevel/file.txt"),
                new File(path + "testFolderForJava/testDirectory6/oneLevel/twoLevel/threeLevel/file.exe")};

        for (File folder : directory) {
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        for (File file : expected) {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
    }

    @Test
    public void files() {
        List<File> fileList = scanning.files(path + "testFolderForJava", listExt);
        assertArrayEquals(expected, fileList.toArray(new File[0]));
    }
}