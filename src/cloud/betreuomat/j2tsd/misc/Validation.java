package cloud.betreuomat.j2tsd.misc;

import java.util.regex.Pattern;

public class Validation {

    public static boolean matchClassName(String className) {
        Pattern pattern = Pattern.compile(".*$\\d+");
        return pattern.matcher(className).matches();
    }

}
