package org.xine.qtime.dal.core.exceptions.types;

public final class CoreExceptionType {

	private CoreExceptionType() {
		super();
	}

	public enum ExceptionType {
		DATABASE
		, INTERNAL
		, GENERIC
		, HIBERNATE
		, SERVICE
		, BL_REQUEST
	}

	public enum ExceptionSubType {
		NULL
		, GET
		, LIST
		, LIST_WHERE
		, ADD
		, SAVE
		, DELETE
		, INVALID_ARGUMENTS
		, COMMIT
		, CONFIGURATION
		, CORE
		, CLOSE
		, CONVERT
		, CLONE
		, BUILD
		, LOAD
		, CHECK
		, SYNC
		, EXECUTE
		, NOT_FOUND
		, GENERATE
		, UNDEFINED
		, VALIDATION
	}

}
