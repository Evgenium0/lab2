public class zadacha5 {
    public static void main(String[] args){
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((1[6-9]|[2-9]\\d)\\d{2}|(0[1-9]|1\\d|2[0-9])\\d{2})$";
        String input = "29/02/2000";
        String input2 = "30/04/2003";
        String input3 = "01/01/2003";
        String input4 = "29/02/2001";
        String input5 = "30-04-2003";
        String input6 = "1/1/1899";
        boolean isMatch = input.matches(regex);
        System.out.println(isMatch); // true
        System.out.println(input2.matches(regex)); // true
        System.out.println(input3.matches(regex)); // true
        System.out.println(input4.matches(regex)); // false
        System.out.println(input5.matches(regex)); // false
        System.out.println(input6.matches(regex)); // false
    }
}
