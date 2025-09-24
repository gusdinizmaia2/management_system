package com.gustavo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    private final String secretKey = genValidSecretKey("secret_key");
    private final long expiresInMs = hourToMs(1);

    private long hourToMs (long hours){
        return hours*60*60*1000;
    }

    private String genValidSecretKey (String secretKey){


        var repeats = 1;

        while( secretKey.length() < 22){

            secretKey.repeat(repeats);
            repeats++;
        }

        return secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public long getExpiresInMs() {
        return expiresInMs;
    }

}
