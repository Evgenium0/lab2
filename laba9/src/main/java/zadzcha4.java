public class zadzcha4 {
    public static void main(String[] args) {
        String regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        String input = "#FFFFFF";
        String input2 = "#FF3421";
        String input3 = "#00ff00";
        String input4 = "232323";
        String input5 = "f#fddee";
        String input6 = "#fd2";
        boolean isMatch = input.matches(regex);
        System.out.println(isMatch);
        System.out.println(input2.matches(regex)); // true
        System.out.println(input3.matches(regex)); // true
        System.out.println(input4.matches(regex)); // false
        System.out.println(input5.matches(regex)); // false
        System.out.println(input6.matches(regex)); // false
    }
}
