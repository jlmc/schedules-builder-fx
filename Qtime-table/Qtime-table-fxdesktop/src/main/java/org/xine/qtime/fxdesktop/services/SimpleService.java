/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.services;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * The Class SimpleService.
 * @param <R>
 *            the generic type
 */
public class SimpleService<R> extends Service<R> {

    /** The callable. */
    private Callable<R> callable;

    /**
     * The param.
     * @param call
     *            the callable
     */

    public void execute(final Callable<R> call) {
        reset();
        this.setCallable(call);
        super.start();
    }

    /**
     * Execute.
     * @param call
     *            the callable
     * @param executor
     *            the executor
     */
    public void execute(final Callable<R> call, final Executor executor) {
        super.setExecutor(executor);
        this.execute(call);
    }

    /*
     * (non-Javadoc)
     * @see javafx.concurrent.Service#createTask()
     */
    @Override
    protected Task<R> createTask() {
        return new Task<R>() {

            @Override
            protected R call() throws Exception {
                return getCallable().call();
            }
        };
    }

    /**
     * Gets the callable.
     * @return the callable
     */
    protected Callable<R> getCallable() {
        return this.callable;
    }

    /**
     * Sets the callable.
     * @param callable
     *            the new callable
     */
    private void setCallable(final Callable<R> callable) {
        this.callable = callable;
    }
}
