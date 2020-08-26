package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FilesUtils {
    public static Map<String, String> linesToMap(Path path) throws IOException {
        Map<String, String> result = new HashMap<>();
        Files.lines(path).forEach(
                line -> {
                    String tmpLine = line.trim();
                    if (!tmpLine.startsWith("#")) {
                        String[] items = tmpLine.split(":");
                        result.put(items[0].trim().toLowerCase(), items[1].trim());
                    }
                }
        );
        return result;
    }
}
