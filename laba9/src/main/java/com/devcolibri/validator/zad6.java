package com.devcolibri.validator;

public class zad6 {
    public static void main (String[] args) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email = "user@example.com";
        if (email.matches(regex)) {
            System.out.println("Valid email address");
        } else {
            System.out.println("Invalid email address");
        }
    }

}
