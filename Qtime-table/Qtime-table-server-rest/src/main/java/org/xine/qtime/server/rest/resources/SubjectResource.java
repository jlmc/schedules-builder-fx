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
import org.xine.qtime.dal.core.exceptions.CoreException;
import org.xine.qtime.dal.core.services.SubjectService;
import org.xine.qtime.entities.Subject;

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
import javax.ws.rs.core.Response.Status;
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
    private SubjectService service;

    /**
     * Read.
     * @param id
     *            the id
     * @param uriInfo
     *            the uri info
     * @return the response
     * @throws InterruptedException
     *             the interrupted exception
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") final Integer id, @Context final UriInfo uriInfo)
            throws InterruptedException {
        // Link purchaseLink = Link.fromPath(uriInfo.getRequestUri().toString() +
        // "/purchase").rel("purchase").build();
        // Thread.sleep(5000L);
        // LOGGER.info("read: {0}", uriInfo.getRequestUri().toString());
        LOGGER.info("Read Subject Request: {}", id);

        try {
            final Subject s = this.service.read(Long.valueOf(id.longValue()));
            return Response.ok(s).build();
        } catch (final RuntimeException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        // return Response.ok(readObject(id))
        // .links(purchaseLink)
        // .link(uriInfo.getRequestUri().toString() + "/genre/programming", "genre")
        // .build();
    }

    /**
     * List.
     * @return the response
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        LOGGER.info("List of Subjects");
        final List<Subject> subjects = this.service.list();

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
        LOGGER.info("Update Subject Request: {}", subject.getId());
        try {
            final Subject s = this.service.update(subject);
            return Response.ok(s).build();

        } catch (final CoreException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessageKey()).build();
        } catch (final Exception e) {
            LOGGER.error("inepected error: [{}] - {}", e.getMessage(), e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

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
        LOGGER.info("Create a new Subject Request ");
        try {
            this.service.save(subject);
        } catch (final CoreException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessageKey()).build();
        } catch (final Exception e) {
            LOGGER.error("inepected error: [{}] - {}", e.getMessage(), e.getCause());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(subject).build();
    }

    /**
     * Delete.
     * @param id
     *            the id
     * @return the response
     */
    @DELETE
    @Path("{id}")
    // @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") final Integer id) {
        LOGGER.info("DELETE Subject [id:{}]", id);

        try {
            this.service.delete(new Subject(new Long(id.longValue()), null, null, null));
        } catch (final CoreException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessageKey()).build();
        } catch (final RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();

    }

}
