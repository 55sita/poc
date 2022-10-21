package com.handicraftsnepal.shecrafts.controller;

import com.handicraftsnepal.shecrafts.entities.TestClass;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello world! some problems are frustrating.";
    }
}