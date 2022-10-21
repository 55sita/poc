package com.handicraftsnepal.shecrafts.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
//import jdk.nashorn.internal.objects.annotations.Property;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    final static String REALM="example";
    final static String AUTHENTICATION_SCHEME= "Bearer";

//    @Inject
//    private String jwtsecret;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //get the authorization header form the request
        String authorizationHeader=requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        //validate the authorization header
        if(!isTokenBasedAuthentication(authorizationHeader)){
            abortWithUnauthorized(requestContext);
            return;
        }
        //Extract the token from the Authorization header
        String token=authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try{
            //validate the token
            validateToken(token);
        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader){
        //check if the Authorization header is valid
        //It must not be null and must be prefixed with "Bearer" plus a whitespace
        //the authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext){
        //Abort the fileter chain with a 401 status code response
        //The WWW=Authenticate header is sent along with the response
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"").build());
    }

    private void validateToken(String token) throws Exception{
        //check if the token was issued by the server and if it's not expired
        //throw an exception if the token is invalid
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);

//            Claim usernameClaim = jwt.getClaim("username");
//            String username = usernameClaim.asString();

        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }

    }
}
