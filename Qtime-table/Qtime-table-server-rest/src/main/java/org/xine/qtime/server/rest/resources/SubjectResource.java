package org.xine.qtime.server.rest.resources;

import java.util.ArrayList;
import java.util.List;

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

import org.xine.qtime.server.rest.entities.Subject;


/**
 * The Class SubjectResource.
 * http://localhost:8080/Qtime-table-server-rest-0.0.1/resources/subjects
 */
@Path("subjects")
public class SubjectResource {


  /**
   * List.
   *
   * @return the response
   */
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response list(){
    List<Subject> subjects = new ArrayList<>();
    for(int i = 0; i< 3; i++){
      Subject subject = new Subject();
      subject.setId(10L+i);
      subject.setAcronym("Acronym");
      subject.setDescription("description");
      subject.setName("name-"+i);
      subjects.add(subject);
    }
    //TODO:: missing subjects fill implementation.

    ResponseBuilder rb = Response.ok(subjects);
    return rb.build();
  }


  /**
   * Update.
   *
   * @param subject the subject
   * @return the response
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(Subject subject){
    //TODO:: update implementation missing
    
    ResponseBuilder rb = Response.ok(subject);
    
    //Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " ).build();
    
    return rb.build();
  }
  
  
  /**
   * Creates the.
   *
   * @param subject the subject
   * @return the response
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(Subject subject){
    //TODO:: create implementation missing
    return Response.ok(subject).build();
  }
  
//  @DELETE
//  @Consumes(MediaType.APPLICATION_JSON)
//  public Response delete(Subject subject){
//    
//    return Response.ok().build();
//  }
  
  
  /**
 * Delete.
 *
 * @param id the id
 * @return the response
 */
  @DELETE
  @Path("{id}")
  //@Consumes(MediaType.APPLICATION_JSON)
  public Response delete(@PathParam("id") Integer id){
    
    return Response.ok().build();
  }
  
  
  
}
