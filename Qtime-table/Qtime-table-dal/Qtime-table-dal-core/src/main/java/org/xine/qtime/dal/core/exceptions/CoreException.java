package org.xine.qtime.dal.core.exceptions;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType.ExceptionSubType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType.ExceptionType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionUtil;


public class CoreException extends Exception implements ICoreException {

    private static final long serialVersionUID = 1L;

    private final CoreExceptionType.ExceptionType type;
    private final CoreExceptionType.ExceptionSubType subType;

    private final String CoreExceptionMessage;

    private String messagekey;

    CoreException(final String message, final Throwable cause,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        super(CoreExceptionUtil.generateMessage(message, cause, type, subType), cause);
        final StackTraceElement[] stackTrace = Arrays.copyOfRange(getStackTrace(), 1,
                getStackTrace().length);
        setStackTrace(stackTrace);
        this.CoreExceptionMessage = message == null ? getMessage(cause) : message;
        this.type = type;
        this.subType = subType;

        logException(stackTrace);
    }

    CoreException(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType, String messageKey) {
        super(CoreExceptionUtil.generateMessage(message, de, type, subType), de.getCause());
        this.CoreExceptionMessage = message == null ? de.getMessage() : message;
        this.type = type == null ? de.getType() : type;
        this.subType = subType;
        this.messagekey = messageKey == null || messageKey.trim().isEmpty() ? "undefined" : messageKey;
        setStackTrace(de.getStackTrace());
    }

    public CoreException(String message, ICoreException de,
			ExceptionType type, ExceptionSubType subType) {
		this(message, de, type, subType, null);
	}

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

    private void logException(final StackTraceElement[] stackTrace) {
        Class<?> clazz;
        try {
            clazz = Class.forName(stackTrace[0].getClassName());
        } catch (final ClassNotFoundException ex) {
            clazz = this.getClass();
        }

        LoggerFactory.getLogger(clazz).error(getMessage(), this);
    }

	@Override
	public String getMessageKey() {
		return this.messagekey;
	}
}
