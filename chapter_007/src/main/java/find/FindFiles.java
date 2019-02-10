package find;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The program finds files.
 * It finds data in the directory and subdirectory.
 * Name's file for find write full, extension and regular expression.
 * The program packs in compiles in jar and works through console example:
 * java find.FindFiles -d d:/ -n txt -m -o log.txt
 * Keys
 * -d - directory of which starts find.
 * -n - name of file for find, extension or regular expression.
 * -m - set of find: expression, -f - full name or -r regular expression.
 * -o - result writes in file.
 * The program writes result in file.
 * <p>
 * Class FindFiles
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since 10.02.19
 */

public class FindFiles {

    /**
     * The method scanning files in the directory.
     *
     * @param parent directory for find.
     * @return list of all files int this directory.
     */
    private Iterable<File> getAllFileAsList(String parent) {
        ArrayList<File> result = new ArrayList<>();
        Deque<File> filesList = new ArrayDeque<>();
        filesList.offer(new File(parent));
        File temp;
        while (!filesList.isEmpty()) {
            temp = filesList.pollFirst();
            if (temp.isDirectory() && !temp.isHidden()) {
                filesList.addAll(Arrays.asList(temp.listFiles()));
            } else if (temp.toString().length() < 4) {
                filesList.addAll(Arrays.asList(temp.listFiles()));
            } else {
                result.add(temp);
            }
        }
        return result;
    }


    /**
     * This method find files;
     */
    public void find(String parent, String name, String paramFind, String nameResFind) throws IOException {
        ArrayList<File> result = new ArrayList<>();
        Iterable<File> fileList = getAllFileAsList(parent);
        String fileExtension;
        if (paramFind.equals("m")) {
            for (File file : fileList) {
                fileExtension = file.getName().substring(file.getName().indexOf(".") + 1);
                if (name.contains(fileExtension)) {
                    result.add(file);
                }
            }
        } else if (paramFind.equals("f")) {
            for (File file : fileList) {
                if (file.toString().equals(name)) {
                    result.add(file);
                }
            }
        } else if (paramFind.equals("r")) {
            for (File file : fileList) {
                if (file.toString().contains(name)) {
                    result.add(file);
                }
            }
        }
        writesResultFindInFile(result, parent, nameResFind);
    }

    /**
     * Writes result of find in the file;
     *
     * @param files       list of  found files;
     * @param path        directory where result file will be;
     * @param nameResFind name result file;
     * @throws IOException
     */
    public void writesResultFindInFile(List<File> files, String path, String nameResFind) throws IOException {
        File file = new File(path + nameResFind);
        file.createNewFile();
        String lineSeparator = System.getProperty("line.separator");
        try (FileWriter fw = new FileWriter(file)) {
            for (File f : files) {
                fw.write(f.toString());
                fw.write(lineSeparator);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String directory = "";
        String nameOfFileForFind = "";
        String paramFind = "";
        String nameResultFile = "";
        FindFiles findFiles = new FindFiles();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[++i];
            }
            if (args[i].equals("-n")) {
                nameOfFileForFind = args[++i];
            }
            if (args[i].equals("-m")) {
                paramFind = "m";
                ++i;
            } else if (args[i].equals("-f")) {
                paramFind = "f";
                ++i;
            } else if (args[i].equals("-r")) {
                paramFind = "r";
                ++i;
            }
            if (args[i].equals("-o")) {
                nameResultFile = args[++i];
            }
        }
        findFiles.find(directory, nameOfFileForFind, paramFind, nameResultFile);
    }
}