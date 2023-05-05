///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
// */
//package JWTrest;
//
//import static JWTrest.Constants.REMEMBER_ME_VALIDITY_SECONDS;
////import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
//import java.io.Serializable;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.SignatureException;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//import static java.util.stream.Collectors.joining;
//import javax.annotation.PostConstruct;
//import java.security.*;
//import io.jsonwebtoken.*;
//
///**
// *
// * @author harsh
// */
////@SessionScoped
//@Named
//public class TokenProvider implements Serializable {
//    /**
//     * Creates a new instance of TokenProvider
//     */
//    private static final Logger LOGGER = Logger.getLogger(TokenProvider.class.getName());
//
//    private static final String AUTHORITIES_KEY = "auth";
//
//    private String secretKey;
////    private String privateKey;
////    private String publicKey;
////    private PrivateKey myprivateKey;
////    private PublicKey mypublicKey;
//    private long tokenValidity;
//
//    private long tokenValidityForRememberMe;
//
//    @PostConstruct
//    public void init() {
//        // load from config
//        this.secretKey = "my-secret-jwt-key";
//        this.tokenValidity = TimeUnit.HOURS.toMillis(10);   //10 hours
//        this.tokenValidityForRememberMe = TimeUnit.SECONDS.toMillis(REMEMBER_ME_VALIDITY_SECONDS);   //24 hours
//    }
//    
//    public String createToken(String username, Set<String> authorities, Boolean rememberMe){
//        long now = (new Date()).getTime();
//        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuer("localhost")
//                .claim(AUTHORITIES_KEY, authorities.stream().collect(joining(",")))
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .setExpiration(new Date(now + validity))
//                .compact();
//    }
//    public JWTCredential getCredential(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//               // .setSigningKey(mypublicKey)
//                .parseClaimsJws(token)
//                .getBody();
//        Set<String> authorities
//                = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .stream()
//                        .collect(Collectors.toSet());
//
//        return new JWTCredential(claims.getSubject(), authorities);
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            System.out.println("TokenProvider - TokenProvider - validate token");
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
//            return true;
//        }catch (Exception e) {
//            LOGGER.log(Level.INFO, "Invalid JWT signature: {0}", e.getMessage());
//            return false;
//        }
//    }
//}
