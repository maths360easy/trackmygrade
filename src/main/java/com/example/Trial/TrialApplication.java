package com.example.Trial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class TrialApplication {


public static void main(String[] args) {
    SpringApplication.run(TrialApplication.class, args);

    // TEMPORARY CODE TO GENERATE HASH
    String raw = "Admin1234";   
    String hash = BCrypt.hashpw(raw, BCrypt.gensalt());
    System.out.println("HASH = " + hash);
}


}
