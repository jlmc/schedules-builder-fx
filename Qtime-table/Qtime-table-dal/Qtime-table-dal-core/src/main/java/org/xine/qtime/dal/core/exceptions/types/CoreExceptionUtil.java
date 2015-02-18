package org.xine.qtime.dal.core.exceptions.types;

import org.xine.qtime.dal.core.exceptions.ICoreException;

public final class CoreExceptionUtil {
    /**
     * Generate message.
     * @param message
     *            the message
     * @param de
     *            the IDalException
     * @param type
     *            the type
     * @param subType
     *            the subType
     * @return the string in the format [<type>_<subType>] '<message>'
     */
    public static String generateMessage(final String message, final ICoreException de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        final StringBuilder sReturn = new StringBuilder();
        sReturn.append("[");
        if (type == null) {
            sReturn.append(de.getType());
        } else {
            sReturn.append(type);
        }
        sReturn.append("_");
        if (subType == null) {
            sReturn.append(de.getSubType());
        } else {
            sReturn.append(subType);
        }
        sReturn.append("] '");
        if (message == null) {
            sReturn.append(de.getMessage());
        } else {
            sReturn.append(message);
        }
        sReturn.append("'");
        return new String(sReturn);
    }

    /**
     * Generate message.
     * @param message
     *            the message
     * @param de
     *            the Throwable
     * @param type
     *            the type
     * @param subType
     *            the subType
     * @return the string in the format {<ThrowableMessage>} [<type>_<subType>] '<message>'
     */
    public static String generateMessage(final String message, final Throwable de,
            final CoreExceptionType.ExceptionType type,
            final CoreExceptionType.ExceptionSubType subType) {
        StringBuilder sReturn;
        sReturn = new StringBuilder();

        if (de != null) {
            sReturn.append("{");
            sReturn.append(getMessage(de));
            sReturn.append("} ");
        }
        sReturn.append("[");
        if (type == null) {
            sReturn.append("UNKNOWN");
        } else {
            sReturn.append(type);
        }
        sReturn.append("_");
        if (subType == null) {
            sReturn.append("UNKNOWN");
        } else {
            sReturn.append(subType);
        }
        sReturn.append("] '");
        if (message == null && de != null) {
            sReturn.append(de.getMessage());
        } else {
            sReturn.append(message);
        }
        sReturn.append("'");
        return new String(sReturn);
    }

    /**
     * Gets the message.
     * @param cause
     *            the cause
     * @return the message
     */
    public static String getMessage(final Throwable cause) {
        if (cause == null) {
            return "Unknown cause";
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
     * Constructor.
     */
    private CoreExceptionUtil() {
        super();
    }
}
