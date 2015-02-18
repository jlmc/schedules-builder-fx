package org.xine.qtime.dal.core.exceptions.types;

/**
 * The Class CoreExeptionType.
 */
public final class CoreExceptionType {

    /**
     * Instantiates a new core exeption type.
     */
    private CoreExceptionType() {
        super();
    }

    /**
     * The Enum ExceptionType.
     */
    public enum ExceptionType {
        /** The database. */
        DATABASE
        /** The internal. */
        , INTERNAL
        /** The generic. */
        , GENERIC
        /** The hibernate. */
        , HIBERNATE
        /** The spring. */
        , SPRING

        ,
        /** The service. */
        SERVICE

        /** When receiving null arguments from BS. */
        , BL_REQUEST
    }

    /**
     * Enumerator to define sub type Exception.
     * Sub Types
     */
    public enum ExceptionSubType {
        /** The null arguments. */
        NULL
        /** The get sub type. */
        , GET
        /** The list sub type. */
        , LIST
        /** The list_where from model. */
        , LIST_WHERE
        /** The add sub type. */
        , ADD
        /** The save sub type. */
        , SAVE
        /** The delete sub type. */
        , DELETE
        /** The wrong arguments. */
        , INVALID_ARGUMENTS
        /** The commit. */
        , COMMIT
        /** The configuration. */
        , CONFIGURATION
        /** The core sub type. */
        , CORE
        /** The close. */
        , CLOSE
        /** The convert. */
        , CONVERT
        /** The clone. */
        , CLONE
        /** The build. */
        , BUILD
        /** The load. */
        , LOAD
        /** The check. */
        , CHECK
        /** The sync. */
        , SYNC
        /** The execute. */
        , EXECUTE
        /** The not found. */
        , NOT_FOUND
        /** The generate. */
        , GENERATE
        /** Undefined. */
        , UNDEFINED
    }

}
