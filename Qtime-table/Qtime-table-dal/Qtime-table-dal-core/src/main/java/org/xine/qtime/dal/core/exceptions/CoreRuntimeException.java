/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
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
	
	/** The message key. */
	private String messageKey;

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
     *
     * @param message            the message
     * @param de            the CoreException
     * @param type            the type
     * @param subType            the sub type
     * @param messageKey the message key
     */
    CoreRuntimeException(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType, final String messageKey) {
        super(CoreExceptionUtil.generateMessage(message, de, type, subType));

        this.CoreExceptionMessage = message == null ? de.getMessage() : message;
        this.type = type == null ? de.getType() : type;
        this.subType = subType == null ? de.getSubType() : subType;
        
        this.messageKey = messageKey == null || messageKey.trim().isEmpty() ? "undefined" : messageKey;
        setStackTrace(de.getStackTrace());
    }

    /**
     * Instantiates a new core runtime exception.
     *
     * @param message the message
     * @param de the de
     * @param type the type
     * @param subType the sub type
     */
    public CoreRuntimeException(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
		this(message, de, type, subType, null);
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

	/* (non-Javadoc)
	 * @see org.xine.qtime.dal.core.exceptions.ICoreException#getMessageKey()
	 */
	@Override
	public String getMessageKey() {
		return this.messageKey;
	}

}
