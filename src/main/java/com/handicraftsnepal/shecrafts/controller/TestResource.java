package com.handicraftsnepal.shecrafts.controller;

import com.handicraftsnepal.shecrafts.entities.TestClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class TestResource {
    private TestClass testClass;
    @GET
    public String testLombok(){
        
        return null;
    }
}
