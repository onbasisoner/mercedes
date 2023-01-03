package utils.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<File> getFileListWithDirectoryNameAndEndWith(String directoryName, String endWith) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(getFileListWithDirectoryNameAndEndWith(file.getAbsolutePath(), endWith));
            }
        }
        //System.out.println(fList);
        return resultList.stream().filter(Object -> Object.getName().endsWith(endWith)).collect(Collectors.toList());
    }

    public static String fileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }
}
