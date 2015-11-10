package org.xine.qtime.client.fx.services;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SimpleService<R> extends Service<R> {

    private Callable<R> callable;

    public void execute(final Callable<R> call) {
        reset();
        this.setCallable(call);
        super.start();
    }

    public void execute(final Callable<R> call, final Executor executor) {
        super.setExecutor(executor);
        this.execute(call);
    }

    @Override
    protected Task<R> createTask() {
        return new Task<R>() {

            @Override
            protected R call() throws Exception {
                return getCallable().call();
            }
        };
    }

    protected Callable<R> getCallable() {
        return this.callable;
    }

    private void setCallable(final Callable<R> callable) {
        this.callable = callable;
    }
}
