package com.hms2.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.algorithms.key}")
    private  String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    private Algorithm algorithm;

    @PostConstruct
    public  void postConstruct() throws UnsupportedEncodingException {
//        System.out.println(algorithmKey);
//        System.out.println(issuer);
//        System.out.println(expiry);
          algorithm = Algorithm.HMAC256(algorithmKey);
    }

  public  String generateToken( String username){
        return JWT.create()
              .withClaim("name", username)
              .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
              .withIssuer(issuer)
              .sign(algorithm);
  }
          public String getUsername(String token) {
              DecodedJWT decodeedJWT = JWT.require(algorithm)
                      .withIssuer(issuer)
                      .build()
                      .verify(token);
               return decodeedJWT.getClaim("name").asString();

          }
}
