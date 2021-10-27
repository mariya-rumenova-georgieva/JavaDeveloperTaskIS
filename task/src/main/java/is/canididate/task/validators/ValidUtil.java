package is.canididate.task.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtil {
    public static boolean validateString(String string, String patternTemplate) {
        if (string == null) {
            return true;
        }

        Pattern pattern = Pattern.compile(patternTemplate);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
