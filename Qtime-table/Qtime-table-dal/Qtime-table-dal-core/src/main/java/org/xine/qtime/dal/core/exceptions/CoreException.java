package org.xine.qtime.dal.core.exceptions;

import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionUtil;

import java.util.Arrays;

/**
 * The Class CoreException.
 */
public class CoreException extends Exception implements ICoreException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The type. */
    private final CoreExceptionType.ExceptionType type;
    /** The sub type. */
    private final CoreExceptionType.ExceptionSubType subType;

    /** The Core exception message. */
    private final String CoreExceptionMessage;

    /**
     * The Constructor.
     * @param message
     *            the message
     * @param cause
     *            the cause
     * @param type
     *            the type
     * @param subType
     *            the subType
     */
    CoreException(final String message, final Throwable cause,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        // super(message == null ? generateMessage(getMessage(cause), type) :
        // generateMessage(message, type), cause);
        super(CoreExceptionUtil.generateMessage(message, cause, type, subType), cause);
        final StackTraceElement[] stackTrace = Arrays.copyOfRange(getStackTrace(), 1,
                getStackTrace().length);
        setStackTrace(stackTrace);
        this.CoreExceptionMessage = message == null ? getMessage(cause) : message;
        this.type = type;
        this.subType = subType;

        logException(stackTrace);
    }

    /**
     * The Constructor.
     * @param message
     *            the message
     * @param de
     *            the CoreException
     * @param type
     *            the type
     * @param subType
     *            the sub type
     */
    CoreException(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        super(CoreExceptionUtil.generateMessage(message, de, type, subType), de.getCause());
        this.CoreExceptionMessage = message == null ? de.getMessage() : message;
        this.type = type == null ? de.getType() : type;
        this.subType = subType;
        setStackTrace(de.getStackTrace());
    }

    /**
     * Gets the message.
     * @param cause
     *            the cause
     * @return the message
     */
    private static String getMessage(final Throwable cause) {
        if (cause == null) {
            return null;
        }
        if (cause.getMessage() != null) {
            return cause.getMessage();
        }
        if (cause.getLocalizedMessage() != null) {
            return cause.getLocalizedMessage();
        }
        return cause.getClass().getName();
    }

    /**
     * Gets the type.
     * @return the type
     */
    @Override
    public CoreExceptionType.ExceptionType getType() {
        return this.type;
    }

    /**
     * Gets the type.
     * @return the type
     */
    @Override
    public CoreExceptionType.ExceptionSubType getSubType() {
        return this.subType;
    }

    /**
     * Gets the Core exception message.
     * @return the Core exception message
     */
    @Override
    public String getCoreExceptionMessage() {
        return this.CoreExceptionMessage;
    }

    /**
     * Log exception.
     * @param stackTrace
     *            the stack trace
     */
    private void logException(final StackTraceElement[] stackTrace) {
        Class<?> clazz;
        try {
            clazz = Class.forName(stackTrace[0].getClassName());
        } catch (final ClassNotFoundException ex) {
            clazz = this.getClass();
        }

        LoggerFactory.getLogger(clazz).error(getMessage(), this);
    }
}
