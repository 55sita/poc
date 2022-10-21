package com.handicraftsnepal.shecrafts.controller;

import com.handicraftsnepal.shecrafts.entities.User;
import com.handicraftsnepal.shecrafts.services.GenerateToken;
import com.handicraftsnepal.shecrafts.services.UserServices;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/authenticate")
public class LoginResource {
    @Inject
    private UserServices userServices;

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String userSignup(User user){
        userServices.signup(user);
        return "user created successfully.";
    }

    @Inject
    private GenerateToken generateToken;
    @GET
    @Path("/login/{userName}/{password}")

    public Response loginUser(@PathParam("userName") String userName,@PathParam("password") String password){
        try{
//            authenticate the user using the credetials provided
            boolean check=userServices.authenticate(userName,password);
            if(check){
                //Issue a token for the user
                String token = generateToken.tokenGeneration(userName);
                //return the token on the response
                return Response.ok(token).build();
            }
            else{
                return Response.status(Response.Status.FORBIDDEN).build();

            }

        }catch (Exception e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


//    @GET
//    @Path("/token")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String GeneratedTokenTest(){
//        return generateToken.tokenGeneration();
//    }
}
