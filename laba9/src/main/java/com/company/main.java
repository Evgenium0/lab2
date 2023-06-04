package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

    private static final Pattern DATE_PATTERN = Pattern.compile(

            "^(0[1-9]|[12]\\d|3[01])/([0][1-9]|1[0-2])/((?:1[6-9]|[2-9]\\d)\\d{2})$"
    );

    public static void test() {
        assert true;
        checkString("29/02/2000");
        assert true;
        checkString("30/04/2003");
        assert true;
        checkString("01/01/2003");
        assert false;
        checkString("29/02/2001");
        assert false;
        checkString("30-04-2003");
        assert false;
        checkString("1/1/1899");
    }

    private static boolean checkString(final String dateAsString) {
        Matcher m = DATE_PATTERN.matcher(dateAsString);
        boolean result = m.matches();
        if (result) {
            int day = Integer.parseInt(m.group(1));
            int month = Integer.parseInt(m.group(2));
            int year = Integer.parseInt(m.group(3));
            if (day > 28) {
                // тут нужны проверки на некорректные дни в полученных месяцах, в том числе 29 февраля
            }
        }
        return result;
    }
}