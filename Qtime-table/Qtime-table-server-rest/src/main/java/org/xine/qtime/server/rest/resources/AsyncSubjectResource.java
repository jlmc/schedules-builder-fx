package org.xine.qtime.server.rest.resources;

import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.core.services.SubjectService;

@Path("async/subjects")
public class AsyncSubjectResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectResource.class);

    @Inject
    private SubjectService service;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void list(@Suspended final AsyncResponse asyncResponse) {

        LOGGER.info("ASYNC Subjects List: ");

        final Runnable command = () -> {
		    try {
		        Thread.sleep(2000L);
		    } catch (final InterruptedException e) {
		        e.printStackTrace();
		    }

		    asyncResponse.resume(AsyncSubjectResource.this.service.list());
		};

        Executors.newSingleThreadExecutor().execute(command);
    }
}
