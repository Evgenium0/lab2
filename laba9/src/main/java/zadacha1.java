import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *Написать регулярное выражение, определяющее является ли данная строка
 GUID с или без скобок.
 – пример правильных выражений: e02fd0e4-00fd-090A-ca30-0d00a0038ba0.
 – пример неправильных выражений: e02fd0e400fd090Aca300d00a0038ba0.
 */
public class zadacha1 {
    public static void main(String[] args) {
        String string = "e02fd0e4-00fd-090A-ca30-0d00a0038ba0";
        Pattern pattern = Pattern.compile("(?i)[a-f\\d]{8}-([a-f\\d]{4}-){3}[a-f\\d]{12}");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) System.out.print("Y");
        else System.out.print("N");
    }

}
