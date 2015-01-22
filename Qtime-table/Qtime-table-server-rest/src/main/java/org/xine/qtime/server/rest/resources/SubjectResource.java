/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.server.rest.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xine.qtime.entities.Subject;
import org.xine.qtime.server.rest.services.SubjectDao;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * The Class SubjectResource.
 * http://localhost:8080/Qtime-table-server-rest-0.0.1/resources/subjects
 */
@Path("subjects")
public class SubjectResource {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectResource.class);

    /** The subject dao. */
    @Inject
    private SubjectDao subjectDao;

    
    /**
     * Read.
     *
     * @param id the id
     * @param uriInfo the uri info
     * @return the response
     * @throws InterruptedException the interrupted exception
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id, @Context UriInfo uriInfo) throws InterruptedException {
        //Link purchaseLink = Link.fromPath(uriInfo.getRequestUri().toString() + "/purchase").rel("purchase").build();
        //Thread.sleep(5000L);
    	LOGGER.info("read: {0}",uriInfo.getRequestUri().toString());
    	
    	try {
            final Subject s = this.subjectDao.read(Long.valueOf(id));
            return Response.ok(s).build();
        } catch (final RuntimeException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
//        return Response.ok(readObject(id))
//        		.links(purchaseLink)
//        		.link(uriInfo.getRequestUri().toString() + "/genre/programming", "genre")
//        		.build();
    }
    
   
    
    
    /**
     * List.
     * @return the response
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        LOGGER.info("testing log on Subject Resource");
        final List<Subject> subjects = this.subjectDao.list();

        final ResponseBuilder rb = Response.ok(subjects);
        return rb.build();
    }

    /**
     * Update.
     * @param subject
     *            the subject
     * @return the response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(final Subject subject) {
        // TODO:: update implementation missing

        try {
            final Subject s = this.subjectDao.update(subject);
            return Response.ok(s).build();
        } catch (final RuntimeException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // ResponseBuilder rb = Response.ok(subject);

        // Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: "
        // ).build();

        // return rb.build();
    }

    /**
     * Creates the.
     * @param subject
     *            the subject
     * @return the response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final Subject subject) {
        try {
            this.subjectDao.save(subject);
        } catch (final RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(subject).build();
    }

    /**
     * Delete.
     * @param id
     *            the id
     * @return the response
     */
    // @DELETE
    // @Consumes(MediaType.APPLICATION_JSON)
    // public Response delete(Subject subject){
    // try{
    // this.subjectDao.delete(subject);
    // }catch(RuntimeException e){
    // return Response.status(Response.Status.BAD_REQUEST).build();
    // }
    // return Response.ok().build();
    // }

    @DELETE
    @Path("{id}")
    // @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") final Integer id) {
        try {
            this.subjectDao.delete(new Subject(new Long(id.longValue()), null, null, null));
        } catch (final RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();

    }

}
