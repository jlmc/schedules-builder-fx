package org.xine.qtime.dal.core.exceptions;

import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionUtil;

import java.util.Arrays;

/**
 * The Class CoreRuntimeException.
 */
public class CoreRuntimeException extends RuntimeException implements ICoreException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /**
     * The type.
     */
    private final CoreExceptionType.ExceptionType type;
    /**
     * The sub type.
     */
    private CoreExceptionType.ExceptionSubType subType = null;

    /**
     * The Core exception message.
     */
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
     *            the sub type
     */
    @SuppressWarnings("rawtypes")
    CoreRuntimeException(final String message, final Throwable cause,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        super(CoreExceptionUtil.generateMessage(message, cause, type, subType), cause);
        final StackTraceElement[] stackTrace = Arrays.copyOfRange(getStackTrace(), 1,
                getStackTrace().length);
        setStackTrace(stackTrace);
        this.CoreExceptionMessage = message == null ? CoreExceptionUtil.getMessage(cause) : message;
        this.type = type;
        this.subType = subType;

        Class clazz;
        try {
            clazz = Class.forName(stackTrace[stackTrace.length - 1].getClassName());
        } catch (final ClassNotFoundException ex) {
            clazz = this.getClass();
        }

        LoggerFactory.getLogger(clazz).error(getMessage(), this);
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
    CoreRuntimeException(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        super(CoreExceptionUtil.generateMessage(message, de, type, subType));

        this.CoreExceptionMessage = message == null ? de.getMessage() : message;
        this.type = type == null ? de.getType() : type;
        this.subType = subType == null ? de.getSubType() : subType;
        setStackTrace(de.getStackTrace());
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

}
