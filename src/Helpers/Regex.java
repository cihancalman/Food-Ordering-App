package Helpers;
import java.util.regex.*;


public class Regex {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);


    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

}
