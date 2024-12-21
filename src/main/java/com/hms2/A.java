package com.hms2;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {

        String encodePwd = BCrypt.hashpw("testing", BCrypt.gensalt(5));
        System.out.println(encodePwd);
    }
}
