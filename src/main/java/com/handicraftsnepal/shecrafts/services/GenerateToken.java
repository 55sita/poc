package com.handicraftsnepal.shecrafts.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

public class GenerateToken {
    public String tokenGeneration(String userName){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
//            Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(2).toInstant());
//            String token = JWT.create()
//                    .withExpiresAt(expirationDate)
//                    .withClaim("username", userName)
//                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            return "Couldn't not generate token!";
        }
    }
}
