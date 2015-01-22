/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.server.rest.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.xine.qtime.entities.Subject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The Class SubjectResourceTest.
 */
@SuppressWarnings({ "static-method", "boxing" })
public class SubjectResourceTest {

	/** The Constant HTTP_LOCALHOST_SUBJECTS. */
	private static final String HTTP_LOCALHOST_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest/resources";

	/**
	 * Gets the subject async test with invocation callback.
	 *
	 * @return the subject async test with invocation callback
	 */
	@Test
	public void getSubjectAsyncTestWithInvocationCallback() {
		WebTarget target = ClientBuilder.newClient()
				.target(HTTP_LOCALHOST_SUBJECTS).path("subjects/1");
		target.request().async().get(new InvocationCallback<Subject>() {
			public void completed(Subject subject) {
				System.out.println("" + subject.getId() + " - "
						+ subject.getName());
				assertThat(subject, notNullValue());
			}

			public void failed(Throwable throwable) {
				fail();
			}
		});

		try {
			Thread.sleep(100000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the subject async test with max time.
	 *
	 * @return the subject async test with max time
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws TimeoutException
	 *             the timeout exception
	 */
	@Test
	public void getSubjectAsyncTestWithMAXTime() throws InterruptedException,
			ExecutionException, TimeoutException {
		WebTarget target = ClientBuilder.newClient()
				.target(HTTP_LOCALHOST_SUBJECTS).path("subjects/1");

		Future<Subject> asyncResponse = target.request().async()
				.get(Subject.class);

		assertThat(asyncResponse.get(10L, TimeUnit.SECONDS), notNullValue());

		Thread.sleep(10000L);

		System.out.println(asyncResponse.get().getId() + "-"
				+ asyncResponse.get().getName());

	}

	/**
	 * Gets the subjects async.
	 *
	 * @return the subjects async
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws TimeoutException
	 *             the timeout exception
	 */
	@Test
	public void getSubjectsAsync() throws InterruptedException,
			ExecutionException, TimeoutException {
		WebTarget target2 = ClientBuilder.newClient()
				.target(HTTP_LOCALHOST_SUBJECTS).path("subjects");

		Future<List<Subject>> asyncResponse = target2.request().async()
				.get(new GenericType<List<Subject>>() {
					// NOTHING
				});

		System.out.println("Cancelado: " + asyncResponse.isCancelled()); // false
		Thread.sleep(2000L);
		System.out.println("Finalizado: " + asyncResponse.isDone()); // false
		Thread.sleep(5000L);

		assertThat(asyncResponse.get(10L, TimeUnit.SECONDS), notNullValue());

		System.out.println("--------------------------------");
		for (Subject s : asyncResponse.get()) {
			System.out.println(s.getId() + "-" + s.getName());
		}

	}

	/**
	 * Test list.
	 */
	@Test
	public void testList() {
		// fail("Not yet implemented");

		final Client client = ClientBuilder.newClient();
		final List<Subject> subjects = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Subject>>() {
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

		final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
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

		final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
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

		final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
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
		// .post(Entity.entity(subject, MediaType.APPLICATION_JSON),new
		// GenericType<Subject>(){});

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

		final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").path("{id}").resolveTemplate("id", "10")
				.request().delete();

		response.close();
		// client.close();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Deve conter o livro_javaee7.
	 */
	@Test
	public void testGet() {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(HTTP_LOCALHOST_SUBJECTS);
		WebTarget path = target.path("subjects/{id}");
		WebTarget bookId = path.resolveTemplate("id", "1");

		Builder invocation = bookId.request();
		Subject subject = invocation.get(Subject.class);

		assertThat(subject, notNullValue());
	}
}
