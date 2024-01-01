package Controller;
import java.util.regex.*;


public abstract class Regex {
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

    private static final String PRICE_REGEX = "^[0-9]+$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final Pattern PRICE_PATTERN = Pattern.compile(PRICE_REGEX);

    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePrice(String price){
        Matcher matcher = PRICE_PATTERN.matcher(price);
        return matcher.matches();
    }

}
