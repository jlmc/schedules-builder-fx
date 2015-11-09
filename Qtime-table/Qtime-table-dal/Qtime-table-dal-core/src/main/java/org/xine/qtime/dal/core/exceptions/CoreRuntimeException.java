package org.xine.qtime.dal.core.exceptions;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionUtil;

public class CoreRuntimeException extends RuntimeException implements ICoreException {

    private static final long serialVersionUID = 1L;

    private final CoreExceptionType.ExceptionType type;
    private CoreExceptionType.ExceptionSubType subType = null;
    private final String CoreExceptionMessage;
	private String messageKey;

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

    public CoreRuntimeException(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
		this(message, de, type, subType, null);
	}

    @Override
    public CoreExceptionType.ExceptionType getType() {
        return this.type;
    }

    @Override
    public CoreExceptionType.ExceptionSubType getSubType() {
        return this.subType;
    }

    @Override
    public String getCoreExceptionMessage() {
        return this.CoreExceptionMessage;
    }

	@Override
	public String getMessageKey() {
		return this.messageKey;
	}

}
