package by.it_academy.jd2.utils.countries;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Vitali Tsvirko
 */
public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode = passwordEncoder.encode("admin");

        System.out.println(encode);

    }
}
