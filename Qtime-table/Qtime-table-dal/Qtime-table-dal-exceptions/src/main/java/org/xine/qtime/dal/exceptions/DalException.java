package org.xine.qtime.dal.exceptions;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.exceptions.enumTypes.DalExceptionType;
import org.xine.qtime.dal.exceptions.enumTypes.DalExceptionUtil;

class DalException extends Exception implements IDalException {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -986572596126844572L;

    /** The type. */
    private final DalExceptionType.ExceptionType type;
    /** The sub type. */
    private final DalExceptionType.ExceptionSubType subType;

    /** The dal exception message. */
    private final String dalExceptionMessage;

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
    public DalException(final String message, final Throwable cause, final DalExceptionType.ExceptionType type, final DalExceptionType.ExceptionSubType subType) {
//        super(message == null ? generateMessage(getMessage(cause), type) : generateMessage(message, type), cause);
        super(DalExceptionUtil.generateMessage(message, cause, type, subType), cause);
        final StackTraceElement[] stackTrace = Arrays.copyOfRange(getStackTrace(), 1, getStackTrace().length);
        setStackTrace(stackTrace);
        this.dalExceptionMessage = message == null ? getMessage(cause) : message;
        this.type = type;
        this.subType = subType;

        logException(stackTrace);
    }

    /**
     * The Constructor.
     * @param message
     *            the message
     * @param de
     *            the DalException
     * @param type
     *            the type
     * @param subType
     *            the sub type
     */
    public DalException(final String message, final IDalException de, final DalExceptionType.ExceptionType type, final DalExceptionType.ExceptionSubType subType) {
        super(DalExceptionUtil.generateMessage(message, de, type, subType), de.getCause());
        this.dalExceptionMessage = message == null ? de.getMessage() : message;
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
    public DalExceptionType.ExceptionType getType() {
        return this.type;
    }
    /**
     * Gets the type.
     * @return the type
     */
    @Override
    public DalExceptionType.ExceptionSubType getSubType() {
        return this.subType;
    }

    /**
     * Gets the dal exception message.
     * @return the dal exception message
     */
    @Override
    public String getDalExceptionMessage() {
        return this.dalExceptionMessage;
    }

    /**
     * Log exception.
     * @param stackTrace the stack trace
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
