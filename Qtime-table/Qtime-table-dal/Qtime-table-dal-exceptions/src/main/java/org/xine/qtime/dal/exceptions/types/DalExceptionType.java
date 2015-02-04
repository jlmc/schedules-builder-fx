/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.dal.exceptions.types;

/**
 * The Class DalExceptionType.
 */
public class DalExceptionType {

    /**
     * Enumerator to define each provider by type
     * Types
     * NONE No type is assign
     * ERROR type represents a Error
     * INFORMATION type represents exception as Information
     * WARNING type represents exception as Warning.
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
       
        /** The DAS ORM CORE. */
        , DAS_ORM_CORE
        /** The DAS ORM FACTORY. */
        , DAS_ORM_FACTORY
        /** The DAS ORM MANAGER. */
        , DAS_ORM_MANAGER
        /** The DAS ORM SERVICES AUDIT. */
        , DAS_ORM_SERVICES_AUDIT



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
