package tq.arxsoft.metalmaths.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String[] extractLanguage(String item) {
        String pattern = "\\{(\\w\\w)\\}(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(item);
        if( !m.find() ) {
            return new String[] {"EN", item };
        }
        return new String[] { m.group(1), m.group(2) };
    }
}
