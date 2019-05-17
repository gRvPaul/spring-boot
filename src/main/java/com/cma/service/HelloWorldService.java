package com.cma.service;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author UNGERW
 */
@Component
@Path("/spring-docker/")
public class HelloWorldService {

    @GET
    @Path("/hello")
    public Response test() {
        return Response.status(200).entity("Western Union Page , - welcome Date: 09.05.2019").build();
    }
}
