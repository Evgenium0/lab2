import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zadacha10 {
    public static void main(String[] args) {
        String text = "Prices: 23.78 USD, 1500 RUR, 19.99 EU, 22 UDD, 0.002 USD";
        Pattern pattern = Pattern.compile("\\b(\\d+\\.\\d{2})\\s+(USD|RUR|EU)\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String price = matcher.group(1);
            String currency = matcher.group(2);
            System.out.println(price + " " + currency);
        }
    }
}
