package core.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class DirectoryUtil {

    public static final String TEST_RESOURCE = "src/test/resources";
    public static final String DATAFILES_RESOURCE = TEST_RESOURCE + "/DataFiles";
    public static final String TEST_PROPERTY_FILE = TEST_RESOURCE + "/Testing.properties";

    public static boolean verifyFolderContains(File parentFolder, String toCheck) {
        return new File(parentFolder, toCheck).exists();
    }

    public static void cleanDirectory(String directoryPath) throws IOException {
        FileUtils.cleanDirectory(new File(directoryPath));
    }

    public static String getAbsolutePath(String relativePath) {
        return FileSystems.getDefault().getPath(relativePath).normalize().toAbsolutePath().toString();
    }
}
