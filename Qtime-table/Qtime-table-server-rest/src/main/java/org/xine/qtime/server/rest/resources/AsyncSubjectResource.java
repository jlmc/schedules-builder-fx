/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
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
import org.xine.qtime.server.rest.services.SubjectDao;

/**
 * The Class AsyncSubjectResource.
 */
@Path("async/subjects")
public class AsyncSubjectResource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubjectResource.class);

	/** The subject dao. */
	@Inject
	private SubjectDao subjectDao;

	/**
	 * List.
	 *
	 * @param asyncResponse
	 *            the async response
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public void list(@Suspended final AsyncResponse asyncResponse) {

		LOGGER.info("ASYNC Subjects List: ");

		Runnable command = new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				asyncResponse.resume(subjectDao.list());
			}
		};

		Executors.newSingleThreadExecutor().execute(command);
	}
}
