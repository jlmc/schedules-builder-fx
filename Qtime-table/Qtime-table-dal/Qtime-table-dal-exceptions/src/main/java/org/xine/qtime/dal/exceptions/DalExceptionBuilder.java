package org.xine.qtime.dal.exceptions;

import java.util.regex.Matcher;

import org.hibernate.HibernateException;
import org.xine.qtime.dal.exceptions.types.DalExceptionType;



public final class DalExceptionBuilder {

    /**
     * The message.
     */
    private String message = null;

    /**
     * The type.
     */
    private DalExceptionType.ExceptionType type = null;
    /**
     * The sub type.
     */
    private DalExceptionType.ExceptionSubType subType = null;

    /**
     * The cause.
     */
    private Throwable cause = null;

    /**
     * Instantiates a new dal exception builder.
     */
    private DalExceptionBuilder() {
    }

    /**
     * Inits the.
     *
     * @return the dal exception builder
     */
    public static DalExceptionBuilder init() {
        return new DalExceptionBuilder();
    }

    /**
     * Adds the cause.
     *
     * @param exception the cause
     * @return the dal exception builder
     */
    public DalExceptionBuilder addCause(final Throwable exception) {
        this.cause = exception;
        return this;
    }

    /**
     * Adds the type.
     *
     * @param exceptionType the type
     * @return the dal exception builder
     */
    public DalExceptionBuilder addType(final DalExceptionType.ExceptionType exceptionType) {
        this.type = exceptionType;
        return this;
    }

    /**
     * Adds the type.
     *
     * @param exceptionSubType the sub type
     * @return the dal exception builder
     */
    public DalExceptionBuilder addSubType(final DalExceptionType.ExceptionSubType exceptionSubType) {
        this.subType = exceptionSubType;
        return this;
    }

    /**
     * Adds the message.
     *
     * @param exceptionMessage the message
     * @return the dal exception builder
     */
    public DalExceptionBuilder addMessage(final String exceptionMessage) {
        this.message = exceptionMessage;
        return this;
    }

    /**
     * Adds the message.
     *
     * @param exceptionMessage the message
     * @param os the args list
     * @return the dal exception builder
     */
    public DalExceptionBuilder addMessage(final String exceptionMessage, final Object... os) {
        String aux = exceptionMessage;
        for (Object object : os) {
            if (object == null) {
                aux = aux.replaceFirst("\\{\\}", "null");
            } else {
                aux = aux.replaceFirst("\\{\\}", Matcher.quoteReplacement(object.toString()));
            }
        }
        this.message = aux;
        return this;
    }

    /**
     * Builds the.
     *
     * @return the dal exception
     */
    public DalException build() {
        if (this.type == null) {
            this.type = DalExceptionType.ExceptionType.GENERIC;
        }
        if (this.subType == null) {
            this.subType = DalExceptionType.ExceptionSubType.UNDEFINED;
        }

        if (this.cause != null) {
            if (this.cause instanceof IDalException) {
                return new DalException(this.message, (IDalException) this.cause, this.type, this.subType);
            } else if (this.cause instanceof HibernateException) {
                return new DalException(this.message, this.cause, DalExceptionType.ExceptionType.HIBERNATE, this.subType);
            }
        }

        return new DalException(this.message, this.cause, this.type, this.subType);
    }

    /**
     * Builds the for runtime.
     *
     * @return the dal runtime exception
     */
    public DalRuntimeException buildForRuntime() {
        if (this.type == null) {
            this.type = DalExceptionType.ExceptionType.GENERIC;
        }
        if (this.subType == null) {
            this.subType = DalExceptionType.ExceptionSubType.UNDEFINED;
        }
        if (this.cause != null) {
            if (this.cause instanceof IDalException) {
                return new DalRuntimeException(this.message, (IDalException) this.cause, this.type, this.subType);
            } else if (this.cause instanceof HibernateException) {
                return new DalRuntimeException(this.message, this.cause, DalExceptionType.ExceptionType.HIBERNATE, this.subType);
            }
        }
        return new DalRuntimeException(this.message, this.cause, this.type, this.subType);
    }
}
