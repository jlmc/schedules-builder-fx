package org.xine.qtime.dal.core.exceptions;

import java.util.regex.Matcher;

import org.hibernate.HibernateException;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;

public class CoreExceptionBuilder {

    private String message = null;
    private String messageKey = null;
    private CoreExceptionType.ExceptionType type = null;
    private CoreExceptionType.ExceptionSubType subType = null;

    private Throwable cause = null;

    private CoreExceptionBuilder() {
        super();
    }

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

    public CoreExceptionBuilder addSubType(final CoreExceptionType.ExceptionSubType exceptionSubType) {
        this.subType = exceptionSubType;
        return this;
    }

    public CoreExceptionBuilder addMessage(final String exceptionMessage) {
        this.message = exceptionMessage;
        return this;
    }

    public CoreExceptionBuilder addMessagekey(final String msgKey) {
        this.messageKey = msgKey;
        return this;
    }

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
                        this.subType, this.messageKey);
            } else if (this.cause instanceof HibernateException) {
                return new CoreException(this.message, this.cause,
                        CoreExceptionType.ExceptionType.HIBERNATE, this.subType);
            }
        }

        return new CoreException(this.message, this.cause, this.type, this.subType);
    }

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
