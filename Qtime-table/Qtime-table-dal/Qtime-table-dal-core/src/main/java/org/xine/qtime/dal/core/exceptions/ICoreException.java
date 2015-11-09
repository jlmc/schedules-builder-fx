package org.xine.qtime.dal.core.exceptions;

import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;

public interface ICoreException {

    String getMessage();

    Throwable getCause();

    StackTraceElement[] getStackTrace();

    CoreExceptionType.ExceptionType getType();

    CoreExceptionType.ExceptionSubType getSubType();

    String getCoreExceptionMessage();
    
    String getMessageKey();
}
