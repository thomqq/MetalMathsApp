package tq.arxsoft.metalmaths.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

public class Mp3Cache {

    final static String INFO_SUFFIX = ".txt";
    final static String FILE_SUFFIX = ".txt";


    private String catchPath;
    private IdentityUtil identityUtil;

    public Mp3Cache(String cachePath, IdentityUtil identityUtil) {
        this.catchPath = cachePath;
        this.identityUtil = identityUtil;
    }

    public boolean contain(String formula) {
        String encodeText = identityUtil.generateIdentity(formula);
        return false;
    }

    public void save(String formula, InputStream synthesize) {

    }

    public String getPathFor(String formula) {
        return null;
    }
}
