package com;

public class obschee {
    public static void main(String[] args) {
        String regex = "abcdefghijklmnopqrstuv5320736";
        String input = "abcdefghijklmnopqrstuv5320736";
        boolean isMatch = input.matches(regex);
        System.out.println(isMatch); // true
    }
}
