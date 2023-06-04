public class zadacha9 {

    private static boolean isSixDigitNumber(String string) {
        if (string.length() != 6) {  // Проверяем длину строки
            return false;
        }
        if (!string.matches("[0-9]+")) {  // Проверяем, что все символы являются цифрами
            return false;
        }
        if (string.charAt(0) == '0') {  // Проверяем, что первый символ не является нулем
            return false;
        }
        return true;
    }

}
