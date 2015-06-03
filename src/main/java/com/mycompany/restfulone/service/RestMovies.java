/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restfulone.service;

import com.mycompany.restfulone.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.GenericEntity;

/**
 * REST Web Service
 *
 * @author vasigorc
 */
@Path("services")
@javax.enterprise.context.RequestScoped
public class RestMovies {
    @Inject
    private StaxParserForWebServices parser;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestMovies
     */
    public RestMovies() {
    }

    @GET
    @Path("actors/{name}")
    @Produces("application/xml")
    public ListWrapper getActorMovies(@DefaultValue("Adam Sandler") @PathParam("name") String name) {
        ListWrapper lw = new ListWrapper();
        lw.getGroup().addAll(parser.getActorMovies(name));
        return lw;
    }

    @GET
    @Path("directors/{name}")
    @Produces("application/xml")
    public ListWrapper getDirectorMovies(@DefaultValue("Frank Coraci") @PathParam("name") String name){
        ListWrapper lw = new ListWrapper();
        lw.getGroup().addAll(parser.getDirectorMovies(name));
        return lw;
    }
    
    @GET
    @Path("movies/{name}")
    @Produces("application/xml")
    public Movie getMovieInfo(@DefaultValue("Click") @PathParam("name") String name){
        try {
            Movie aMovie = parser.getMovieInfo(name);
            return aMovie;            
        } catch (IOException ex) {
            Logger.getLogger(RestMovies.class.getName()).log(Level.INFO, ex.getMessage());
        }
        return null;
    }
}
