package org.xine.qtime.server.rest.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.xine.qtime.server.rest.entities.Subject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class SubjectResourceTest {

  private static final String HTTP_LOCALHOST_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest-0.0.1/resources";

  @Test
  public void testList() {
    //fail("Not yet implemented");
    
    Client client = ClientBuilder.newClient();
    List<Subject> subjects = client.target(HTTP_LOCALHOST_SUBJECTS)
                             .path("subjects")
                             .request(MediaType.APPLICATION_JSON)
                             .get(new GenericType<List<Subject>>(){});
    
    //assertThat(subjects, ));
    assertTrue(subjects != null && !subjects.isEmpty());
  }

  @Test
  public void testUpdate() {
    //fail("Not yet implemented");
    //POST
    
    Client client = ClientBuilder.newClient();
    
    Subject subject = new Subject();
    subject.setId(1L);
    subject.setAcronym("AXRU");
    subject.setDescription("AXR description update");
    subject.setName("astros xinos rascos update");
    
    
    Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
                              .path("subjects")
                              .request(MediaType.APPLICATION_JSON)
                              .put(Entity.entity(subject, MediaType.APPLICATION_JSON));
    
    response.close();
    client.close();
    
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testCreate() {
    //fail("Not yet implemented");
    //POST
    
    Client client = ClientBuilder.newClient();
    
    Subject subject = new Subject();
    subject.setId(1L);
    subject.setAcronym("AXR");
    subject.setDescription("AXR description");
    subject.setName("astros xinos rascos");
    
    
    Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
                              .path("subjects")
                              .request(MediaType.APPLICATION_JSON)
                              .post(Entity.entity(subject, MediaType.APPLICATION_JSON));


    
    response.close();
    client.close();
  
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
  }
  
  @Test
  public void testCreate2() {
    //fail("Not yet implemented");
    //POST
    
    Client client = ClientBuilder.newClient();
    
    Subject subject = new Subject();
    subject.setId(1L);
    subject.setAcronym("AXR");
    subject.setDescription("AXR description");
    subject.setName("astros xinos rascos");
    
    
    Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
        .path("subjects")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(subject, MediaType.APPLICATION_JSON));
    
    Subject out = response.readEntity(Subject.class); 
    
//    Subject s = client.target(HTTP_LOCALHOST_SUBJECTS)
//    .path("subjects")
//    .request(MediaType.APPLICATION_JSON).post(Entity.entity(subject, MediaType.APPLICATION_JSON))
//    .readEntity(Subject.class);
    
    
//    
//    Subject subjectOut = client.target(HTTP_LOCALHOST_SUBJECTS)
//        .path("subjects")
//        .request(MediaType.APPLICATION_JSON)
//        .post(Entity.entity(subject, MediaType.APPLICATION_JSON),new GenericType<Subject>(){});
    
    //response.close();
   
    client.close();
    
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertNotNull(out);
  }

  @Test
  public void testDelete() {
    //fail("Not yet implemented");
    Client client = ClientBuilder.newClient();
    
    Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
                              .path("subjects")
                              .path("{id}")
                              .resolveTemplate("id", "10")
                              .request()
                              .delete();
    
    response.close();
    //client.close();
    
    assertEquals(Status.OK.getStatusCode(), response.getStatus());
  }

}
