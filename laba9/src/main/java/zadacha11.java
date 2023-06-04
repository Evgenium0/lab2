import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zadacha11 {
    public static void main(String[] args) {
        String text = "(3 + 5) - 9 × 4, 2 * 9 - 6 × 5";
        Pattern pattern = Pattern.compile("\\b\\d+\\b(?!\\s*\\+)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String number = matcher.group();
            System.out.println(number);
        }
    }
}
