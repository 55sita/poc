package com.handicraftsnepal.shecrafts.controller;

import com.handicraftsnepal.shecrafts.entities.HandicraftBy;
import com.handicraftsnepal.shecrafts.services.HandicraftByServices;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/insertmanufacturer")
public class ManufacturerController {
    @Inject
    private HandicraftByServices manufacturer;
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertManu(HandicraftBy handicraftBy){
        manufacturer.insert(handicraftBy);
        return "Manufacturer is successfully created";
    }

}
