package org.xine.qtime.server.rest.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * The Class LogginFilter.
 * An extension interface implemented by container request filters.
 * <p>
 * By default, i.e. if no {@link javax.ws.rs.NameBinding name binding} is applied to the filter
 * implementation class, the filter instance is applied globally, however only after the incoming
 * request has been matched to a particular resource by JAX-RS runtime. If there is a
 * {@link javax.ws.rs.NameBinding &#64;NameBinding} annotation applied to the filter, the filter
 * will also be executed at the <i>post-match</i> request extension point, but only in case the
 * matched {@link javax.ws.rs.HttpMethod
 * resource or sub-resource method} is bound to the same name-binding annotation.
 * </p>
 * <p>
 * In case the filter should be applied at the <i>pre-match</i> extension point, i.e. before any
 * request matching has been performed by JAX-RS runtime, the filter MUST be annotated with a
 * {@link PreMatching &#64;PreMatching} annotation.
 * </p>
 * <p>
 * Use a pre-match request filter to update the input to the JAX-RS matching algorithm, e.g., the
 * HTTP method, Accept header, return cached responses etc. Otherwise, the use of a request filter
 * invoked at the <i>post-match</i> request extension point (after a successful resource method
 * matching) is recommended.
 * </p>
 * <p>
 * Filters implementing this interface must be annotated with {@link javax.ws.rs.ext.Provider
 * &#64;Provider} to be discovered by the JAX-RS runtime. Container request filter instances may
 * also be discovered and bound {@link DynamicFeature dynamically} to particular resource methods.
 * </p>
 * @see PreMatching
 * @see javax.ws.rs.container.ContainerResponseFilter
 */
@Provider
public class LogginFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogginFilter.class);

    /**
     * Filter method called before a request has been dispatched to a resource.
     * <p>
     * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
     * class-level annotation value. If a request filter produces a response by calling
     * {@link ContainerRequestContext#abortWith} method, the execution of the (either pre-match or
     * post-match) request filter chain is stopped and the response is passed to the corresponding
     * response filter chain (either pre-match or post-match). For example, a pre-match caching
     * filter may produce a response in this way, which would effectively skip any post-match
     * request filters as well as post-match response filters. Note however that a responses
     * produced in this manner would still be processed by the pre-match response filter chain.
     * </p>
     * @param requestContext
     *            request context.
     * @throws IOException
     *             if an I/O exception occurs.
     * @see PreMatching
     */
    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        LOGGER.info(requestContext.getMethod());
    }

    /**
     * Filter method called after a response has been provided for a request
     * (either by a {@link ContainerRequestFilter request filter} or by a
     * matched resource method.
     * <p>
     * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
     * class-level annotation value.
     * </p>
     * @param requestContext
     *            request context.
     * @param responseContext
     *            response context.
     * @throws IOException
     *             if an I/O exception occurs.
     */
    @Override
    public void filter(final ContainerRequestContext requestContext,
            final ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub

    }

}
