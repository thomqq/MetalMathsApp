package tq.arxsoft.metalmaths.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesUtils {
    public static Map<String, List<String>> linesToMap(Path path) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        Files.lines(path).forEach(
                line -> {
                    String tmpLine = line.trim();
                    if (!tmpLine.startsWith("#")) {
                        String[] items = tmpLine.split(":");
                        List<String> values =  result.get(items[0].trim().toLowerCase());
                        if( values == null) {
                            values = new ArrayList<>();
                            result.put(items[0].trim().toLowerCase(),values);
                        }
                        values.add(items[1].trim());
                    }
                }
        );
        return result;
    }
}
