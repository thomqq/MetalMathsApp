package tq.arxsoft.metalmaths.domain;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IdentityUtil {

    public String generateIdentity(String formula) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(formula.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
