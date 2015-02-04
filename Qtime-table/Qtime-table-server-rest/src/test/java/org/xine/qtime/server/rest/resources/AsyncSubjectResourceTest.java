/**
 *
 */
package org.xine.qtime.server.rest.resources;

import org.junit.Test;
import org.xine.qtime.entities.Subject;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

/**
 * The Class AsyncSubjectResourceTest.
 * @author jcosta
 */
@SuppressWarnings("static-method")
public class AsyncSubjectResourceTest {

    /** The Constant HTTP_LOCALHOST__ASYNC_SUBJECTS. */
    private static final String HTTP_LOCALHOST__ASYNC_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest/resources/async/subjects";

    /**
     * Test method for
     * {@link org.xine.qtime.server.rest.resources.AsyncSubjectResource#list(javax.ws.rs.container.AsyncResponse)}
     * .
     */

    @Test
    public void testList() {
        final WebTarget target = ClientBuilder.newClient().target(HTTP_LOCALHOST__ASYNC_SUBJECTS);

        target.request().async().get(new InvocationCallback<List<Subject>>() {

            @Override
            public void completed(final List<Subject> response) {
                System.out.println("Completed... with: ");

                for (final Subject s : response) {
                    System.out.println(s.getId() + "\t" + s.getName());
                }

            }

            @Override
            public void failed(final Throwable throwable) {
                System.out.println(throwable);

            }
        });

        System.out.println("Final do metodo principal...");

        try {
            Thread.sleep(20000L);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

    }

}
