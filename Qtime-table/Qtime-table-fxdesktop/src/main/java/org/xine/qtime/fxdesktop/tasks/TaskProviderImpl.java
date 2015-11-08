/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.tasks;

import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.entities.Subject;

import com.google.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * The Class TaskProveiderImpl.
 */
public class TaskProviderImpl implements TaskProvider {

    /** The connector. */
    @Inject
    private SubjectConnector connector;

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.tasks.TaskProvider#getLoadSubjectsListTask()
     */
    @Override
    public Task<ObservableList<Subject>> getLoadSubjectsListTask() {
        return new Task<ObservableList<Subject>>() {
            @SuppressWarnings("synthetic-access")
            @Override
            protected ObservableList<Subject> call() throws Exception {
                return FXCollections.observableArrayList(TaskProviderImpl.this.connector.list());
            }

        };
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.tasks.TaskProvider#getGetSubjectTask(java.lang.Integer)
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public Task<Subject> getGetSubjectTask(final Integer id) {
        return new Task<Subject>() {
            @Override
            protected final Subject call() throws Exception {
                return TaskProviderImpl.this.connector.get(id);
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see
     * org.xine.qtime.fxdesktop.tasks.TaskProvider#getSaveSubjectTask(org.xine.qtime.entities.Subject
     * )
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public Task<Subject> getSaveSubjectTask(final Subject subject) {
        return new Task<Subject>() {

            @Override
            protected Subject call() throws Exception {
                return TaskProviderImpl.this.connector.create(subject);
            }
        };
    }
}
