package org.xine.qtime.dal.core.exceptions;

import org.hibernate.HibernateException;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;

import java.util.regex.Matcher;

/**
 * The Class CoreExceptionBuilder.
 */
public class CoreExceptionBuilder {

    /**
     * The message.
     */
    private String message = null;

    /**
     * The type.
     */
    private CoreExceptionType.ExceptionType type = null;
    /**
     * The sub type.
     */
    private CoreExceptionType.ExceptionSubType subType = null;

    /**
     * The cause.
     */
    private Throwable cause = null;

    /**
     * Instantiates a new core exception builder.
     */
    private CoreExceptionBuilder() {
        super();
    }

    /**
     * Inits the.
     * @return the core exception builder
     */
    public static CoreExceptionBuilder init() {
        return new CoreExceptionBuilder();
    }

    public CoreExceptionBuilder addCause(final Throwable exception) {
        this.cause = exception;
        return this;
    }

    public CoreExceptionBuilder addType(final CoreExceptionType.ExceptionType exceptionType) {
        this.type = exceptionType;
        return this;
    }

    /**
     * Adds the type.
     * @param exceptionSubType
     *            the sub type
     * @return the dal exception builder
     */
    public CoreExceptionBuilder addSubType(final CoreExceptionType.ExceptionSubType exceptionSubType) {
        this.subType = exceptionSubType;
        return this;
    }

    /**
     * Adds the message.
     * @param exceptionMessage
     *            the message
     * @return the dal exception builder
     */
    public CoreExceptionBuilder addMessage(final String exceptionMessage) {
        this.message = exceptionMessage;
        return this;
    }

    /**
     * Adds the message.
     * @param exceptionMessage
     *            the message
     * @param os
     *            the args list
     * @return the dal exception builder
     */
    public CoreExceptionBuilder addMessage(final String exceptionMessage, final Object... os) {
        String aux = exceptionMessage;
        for (final Object object : os) {
            if (object == null) {
                aux = aux.replaceFirst("\\{\\}", "null");
            } else {
                aux = aux.replaceFirst("\\{\\}", Matcher.quoteReplacement(object.toString()));
            }
        }
        this.message = aux;
        return this;
    }

    public CoreException build() {
        if (this.type == null) {
            this.type = CoreExceptionType.ExceptionType.GENERIC;
        }
        if (this.subType == null) {
            this.subType = CoreExceptionType.ExceptionSubType.UNDEFINED;
        }

        if (this.cause != null) {
            if (this.cause instanceof ICoreException) {
                return new CoreException(this.message, (ICoreException) this.cause, this.type,
                        this.subType);
            } else if (this.cause instanceof HibernateException) {
                return new CoreException(this.message, this.cause,
                        CoreExceptionType.ExceptionType.HIBERNATE, this.subType);
            }
        }

        return new CoreException(this.message, this.cause, this.type, this.subType);
    }

    /**
     * Builds the for runtime.
     * @return the Core runtime exception
     */
    public CoreRuntimeException buildForRuntime() {
        if (this.type == null) {
            this.type = CoreExceptionType.ExceptionType.GENERIC;
        }
        if (this.subType == null) {
            this.subType = CoreExceptionType.ExceptionSubType.UNDEFINED;
        }
        if (this.cause != null) {
            if (this.cause instanceof ICoreException) {
                return new CoreRuntimeException(this.message, (ICoreException) this.cause,
                        this.type, this.subType);
            } else if (this.cause instanceof HibernateException) {
                return new CoreRuntimeException(this.message, this.cause,
                        CoreExceptionType.ExceptionType.HIBERNATE, this.subType);
            }
        }
        return new CoreRuntimeException(this.message, this.cause, this.type, this.subType);
    }

}
