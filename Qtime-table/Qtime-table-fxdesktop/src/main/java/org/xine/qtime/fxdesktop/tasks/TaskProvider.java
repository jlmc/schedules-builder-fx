/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.tasks;

import org.xine.qtime.entities.Subject;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * The Interface TaskProveider.
 */
public interface TaskProvider {

    /**
     * Gets the load subjects list task.
     * @return the load subjects list task
     */
    Task<ObservableList<Subject>> getLoadSubjectsListTask();

    /**
     * Gets the gets the subject task.
     * @param id
     *            the id
     * @return the gets the subject task
     */
    Task<Subject> getGetSubjectTask(Integer id);

    /**
     * Gets the save subject task.
     * @param subject
     *            the subject
     * @return the save subject task
     */
    Task<Subject> getSaveSubjectTask(Subject subject);

}
