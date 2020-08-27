package tq.arxsoft.metalmaths.domain;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mp3Cache {

    final static String INFO_SUFFIX = ".txt";
    final static String FILE_SUFFIX = ".mp3";


    private String catchPath;
    private IdentityUtil identityUtil;

    public Mp3Cache(String cachePath, IdentityUtil identityUtil) {
        this.catchPath = cachePath;
        this.identityUtil = identityUtil;
    }

    public boolean contain(String formula, String lang) {
        return getPathFor(formula, lang) != null;
    }

    public void save(String formula, InputStream synthesize, String lang) {
        String fullPathForInfoText = createFullPath(formula) + "_" + lang + INFO_SUFFIX;
        String fullPathForMp3 = createFullPath(formula) + "_" + lang + FILE_SUFFIX;
        try {
            Files.write(Paths.get(fullPathForInfoText), formula.getBytes());
            IOUtils.copy(synthesize, new FileOutputStream(fullPathForMp3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPathFor(String formula, String lang) {
        String fullPath = createFullPath(formula) + "_" +lang + FILE_SUFFIX;
        if( Files.exists(Paths.get(fullPath)) ) {
            return fullPath;
        }
        return null;
    }

    private String createFullPath(String formula) {

        StringBuilder builder = new StringBuilder(catchPath);
        if( !catchPath.endsWith("\\") ) {
            builder.append("\\");
        }
        builder.append(identityUtil.generateIdentity(formula));
        return builder.toString();
    }
}
