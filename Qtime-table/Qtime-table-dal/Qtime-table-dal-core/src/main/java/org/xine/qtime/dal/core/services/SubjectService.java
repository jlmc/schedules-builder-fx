package org.xine.qtime.dal.core.services;

import java.util.List;

import javax.inject.Inject;

import org.xine.qtime.dal.core.daos.SubjectDao;
import org.xine.qtime.dal.core.exceptions.CoreException;
import org.xine.qtime.dal.core.exceptions.CoreExceptionBuilder;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType.ExceptionSubType;
import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType.ExceptionType;
import org.xine.qtime.dal.core.util.Transactional;
import org.xine.qtime.entities.Subject;

public class SubjectService {

    @Inject
    private SubjectDao dao;

    public List<Subject> list() {
        return this.dao.list();
    }

    public Subject read(final Long id) {
        return this.dao.read(id);
    }

    @Transactional
    public Subject save(final Subject s) throws CoreException {

        if (s == null) {
            throw CoreExceptionBuilder.init().addType(ExceptionType.SERVICE)
                    .addSubType(ExceptionSubType.VALIDATION).addMessage("Subject Can't be null")
                    .build();
        }

        if (s.getName() == null || s.getName().trim().isEmpty()) {
            throw CoreExceptionBuilder.init().addType(ExceptionType.SERVICE)
                    .addSubType(ExceptionSubType.VALIDATION).addMessage("Name can't not be null")
                    .addMessagekey(ValidationErrorsTypes.NAME_EMPTY.toString()).build();
        }

        return this.dao.save(s);
    }

    @Transactional
    public Subject update(final Subject s) throws CoreException {
        if (s == null) {
            throw CoreExceptionBuilder.init().addType(ExceptionType.SERVICE)
                    .addSubType(ExceptionSubType.VALIDATION).addMessage("Subject Can't be null")
                    .build();
        }

        if (s.getName() == null || s.getName().trim().isEmpty()) {
            throw CoreExceptionBuilder.init().addType(ExceptionType.SERVICE)
                    .addSubType(ExceptionSubType.VALIDATION).addMessage("Name can't not be null")
                    .addMessagekey(ValidationErrorsTypes.NAME_EMPTY.toString()).build();
        }

        return this.dao.update(s);
    }

    @Transactional
    public void delete(final Subject s) throws CoreException {

        if (s == null) {
            throw CoreExceptionBuilder.init().addType(ExceptionType.SERVICE)
                    .addSubType(ExceptionSubType.VALIDATION).addMessage("Subject Can't be null")
                    .build();
        }

        if (s.getId() == null || s.getId().longValue() <= 0L) {
            throw CoreExceptionBuilder.init().addType(ExceptionType.SERVICE)
            .addSubType(ExceptionSubType.VALIDATION).addMessage("ID is invalalid value")
            .addMessagekey(ValidationErrorsTypes.INVALID_ID.toString()).build();
        }

        this.dao.delete(s);
    }

}
