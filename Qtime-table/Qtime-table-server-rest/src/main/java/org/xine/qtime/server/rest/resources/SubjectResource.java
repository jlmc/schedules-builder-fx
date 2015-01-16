package org.xine.qtime.server.rest.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xine.qtime.server.rest.entities.Subject;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * The Class SubjectResource.
 * http://localhost:8080/Qtime-table-server-rest-0.0.1/resources/subjects
 */
@Path("subjects")
public class SubjectResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectResource.class);

    /** The subject dao. */
    @Inject
    private SubjectDao subjectDao;

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
