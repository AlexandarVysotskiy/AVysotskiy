package manager;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerFileManagerTest {
    private static final String LS = System.getProperty("line.separator");
    private String path;
    private File[] directory;
    private File fileForCopy;

    @Before
    public void setUp() throws IOException {
        path = System.getProperty("java.io.tmpdir") + "testFolderForJava\\";

        fileForCopy = new File(path + "fileForTest.txt");

        directory = new File[]{new File(path + "testDirectory1/"),
                new File(path + "testDirectory2/oneLevel/twoLevel/"),
                new File(path + "testDirectory3/oneLevel/twoLevel/")};

        for (File folder : directory) {
            if (!folder.exists()) {
                System.out.println(folder);
                folder.mkdirs();
            }
        }
    }

    public void serverFileManagerTest(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ServerFileManager server = new ServerFileManager(socket);
        server.start(path);
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserWriteStopThenShowListFilesAndEnd() throws IOException {
        serverFileManagerTest("stop",
                Joiner.on(LS).join("1 " + path + "testDirectory1", "2 " + path
                        + "testDirectory2", "3 " + path + "testDirectory3" + LS + LS));
    }

    @Test
    public void showListFilesThenUserInputTwoThenCopyFile() throws IOException {
        fileForCopy.createNewFile();
        String pathOfFileForCopy = fileForCopy.toString();
        File newFileForTest = new File(path + "testDirectory1\\fileForTest.txt");
        assertFalse(newFileForTest.exists());
        serverFileManagerTest(Joiner.on(LS).join(2, pathOfFileForCopy, "stop"),
                Joiner.on(LS).join("1 " + path + "fileForTest.txt", "2 " + path + "testDirectory1", "3 " + path
                        + "testDirectory2", "4 " + path + "testDirectory3" + LS, LS + LS));
        assertTrue(newFileForTest.exists());
        newFileForTest.delete();
        fileForCopy.delete();
    }
}
