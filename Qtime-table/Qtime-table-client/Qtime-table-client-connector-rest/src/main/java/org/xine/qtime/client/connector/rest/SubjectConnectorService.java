/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.client.connector.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



import org.xine.qtime.client.connector.ConnectorException;
import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.entities.Subject;

/**
 * The Class SubjectConnectorService.
 */
public class SubjectConnectorService implements SubjectConnector {

	/** The Constant HTTP_LOCALHOST_SUBJECTS. */
	private static final String HTTP_LOCALHOST_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest/resources";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.client.connector.Connector#list()
	 */
	@Override
	public List<Subject> list() throws ConnectorException {

		final Client client = ClientBuilder.newClient();
		final List<Subject> subjects = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Subject>>() {
					// NOTHING
				});

		return subjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.client.connector.Connector#update(java.lang.Object)
	 */
	@Override
	public Subject update(Subject subject) throws ConnectorException {
		try {
			final Client client = ClientBuilder.newClient();
			final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
					.path("subjects").request(MediaType.APPLICATION_JSON)
					.put(Entity.entity(subject, MediaType.APPLICATION_JSON));

			final Subject result = response.readEntity(Subject.class);
			response.close();
			client.close();

			if (Status.OK.getStatusCode() == response.getStatus()) {
				return result;
			}
			throw new ConnectorException(response.getStatus());
		} catch (ConnectorException e) {
			throw e;
		} catch (Exception e) {
			throw new ConnectorException(Integer.MIN_VALUE);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.client.connector.Connector#create(java.lang.Object)
	 */
	@Override
	public Subject create(Subject subject) throws ConnectorException {
		try {
			final Client client = ClientBuilder.newClient();
			final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
					.path("subjects").request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(subject, MediaType.APPLICATION_JSON));

			final Subject result = response.readEntity(Subject.class);

			client.close();

			if (Status.OK.getStatusCode() == response.getStatus()) {
				return result;
			}
			throw new ConnectorException(response.getStatus());
		} catch (ConnectorException e) {
			throw e;
		} catch (Exception e) {
			throw new ConnectorException(Integer.MIN_VALUE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.client.connector.Connector#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ConnectorException {
		try {
			final Client client = ClientBuilder.newClient();

			final Response response = client.target(HTTP_LOCALHOST_SUBJECTS)
					.path("subjects").path("{id}").resolveTemplate("id", "10")
					.request().delete();
			response.close();

			if (Status.OK.getStatusCode() != response.getStatus()) {
				throw new ConnectorException(response.getStatus());
			}

		} catch (ConnectorException e) {
			throw e;
		} catch (Exception e) {
			throw new ConnectorException(Integer.MIN_VALUE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.client.connector.Connector#get(java.lang.Integer)
	 */
	@Override
	public Subject get(Integer id) throws ConnectorException {
		try {
			final Response response = ClientBuilder.newClient()
					.target(HTTP_LOCALHOST_SUBJECTS).path("subjects/{id}")
					.resolveTemplate("id", "1").request().get();
			Subject result = response.readEntity(Subject.class);

			if (Status.OK.getStatusCode() == response.getStatus()) {
				return result;
			}
			throw new ConnectorException(response.getStatus());
		} catch (ConnectorException e) {
			throw e;
		} catch (Exception e) {
			throw new ConnectorException(Integer.MIN_VALUE);
		}
	}

}
