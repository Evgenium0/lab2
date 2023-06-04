import java.util.Stack;

public class zadacha12 {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expression1 = "(3 + 5) - 9 * 4";
        String expression2 = "((3 + 5) - 9 * 4";
        System.out.println(isBalanced(expression1)); // true
        System.out.println(isBalanced(expression2)); // false
    }
}
