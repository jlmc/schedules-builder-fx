package org.xine.qtime.server.rest.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xine.qtime.server.rest.entities.Subject;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * The Class SubjectResourceTest.
 */
@SuppressWarnings({"static-method", "boxing" })
public class SubjectResourceTest {

    /** The Constant HTTP_LOCALHOST_SUBJECTS. */
    private static final String HTTP_LOCALHOST_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest-0.0.1/resources";

    /**
     * Test list.
     */
    @Test
    public void testList() {
        // fail("Not yet implemented");

        final Client client = ClientBuilder.newClient();
        final List<Subject> subjects = client.target(HTTP_LOCALHOST_SUBJECTS).path("subjects")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Subject>>() {
                    // NOTHING
                });

        // assertThat(subjects, ));
        assertTrue(subjects != null && !subjects.isEmpty());
    }

    /**
     * Test update.
     */
    @Test
    public void testUpdate() {
        // fail("Not yet implemented");
        // POST

        final Client client = ClientBuilder.newClient();

        final Subject subject = new Subject();
        subject.setId(1L);
        subject.setAcronym("AXRU");
        subject.setDescription("AXR description update");
        subject.setName("astros xinos rascos update");

        final Response response = client.target(HTTP_LOCALHOST_SUBJECTS).path("subjects")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(subject, MediaType.APPLICATION_JSON));

        response.close();
        client.close();

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test create.
     */
    @Test
    public void testCreate() {
        // fail("Not yet implemented");
        // POST

        final Client client = ClientBuilder.newClient();

        final Subject subject = new Subject();
        subject.setId(1L);
        subject.setAcronym("AXR");
        subject.setDescription("AXR description");
        subject.setName("astros xinos rascos");

        final Response response = client.target(HTTP_LOCALHOST_SUBJECTS).path("subjects")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(subject, MediaType.APPLICATION_JSON));

        response.close();
        client.close();

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test create2.
     */
    @Test
    public void testCreate2() {
        // fail("Not yet implemented");
        // POST

        final Client client = ClientBuilder.newClient();

        final Subject subject = new Subject();
        subject.setId(1L);
        subject.setAcronym("AXR");
        subject.setDescription("AXR description");
        subject.setName("astros xinos rascos");

        final Response response = client.target(HTTP_LOCALHOST_SUBJECTS).path("subjects")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(subject, MediaType.APPLICATION_JSON));

        final Subject out = response.readEntity(Subject.class);

        // Subject s = client.target(HTTP_LOCALHOST_SUBJECTS)
        // .path("subjects")
        // .request(MediaType.APPLICATION_JSON).post(Entity.entity(subject,
        // MediaType.APPLICATION_JSON))
        // .readEntity(Subject.class);

        //
        // Subject subjectOut = client.target(HTTP_LOCALHOST_SUBJECTS)
        // .path("subjects")
        // .request(MediaType.APPLICATION_JSON)
        // .post(Entity.entity(subject, MediaType.APPLICATION_JSON),new GenericType<Subject>(){});

        // response.close();

        client.close();

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(out);
    }

    /**
     * Test delete.
     */
    @Test
    public void testDelete() {
        // fail("Not yet implemented");
        final Client client = ClientBuilder.newClient();

        final Response response = client.target(HTTP_LOCALHOST_SUBJECTS).path("subjects")
                .path("{id}").resolveTemplate("id", "10").request().delete();

        response.close();
        // client.close();

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

}
