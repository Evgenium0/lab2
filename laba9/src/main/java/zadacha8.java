import java.security.SecureRandom;

public class zadacha8 {

    public class PasswordGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
        private static final SecureRandom RANDOM = new SecureRandom();

        public static String generateStrongPassword(int length) {
            StringBuilder password = new StringBuilder();
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasDigit = false;
            while (password.length() < length) {
                char c = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowerCase = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                password.append(c);
            }
            if (!hasUpperCase || !hasLowerCase || !hasDigit) {
                // Если пароль не содержит хотя бы одну заглавную букву, одну маленькую букву и одну цифру, генерируем новый пароль
                return generateStrongPassword(length);
            }
            return password.toString();
        }
    }


}
