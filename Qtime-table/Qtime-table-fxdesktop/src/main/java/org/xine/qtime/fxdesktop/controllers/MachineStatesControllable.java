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
package org.xine.qtime.fxdesktop.controllers;

import java.util.Collection;

/**
 * The Interface MachineStatesControllable.
 * @param <T>
 *            the generic type
 */
public interface MachineStatesControllable<T> {

    /**
     * On back.
     */
    default void onBack() {
        /**/
    }

    /**
     * On added.
     * @param objs
     *            the objs
     */
    default void onAdded(final Collection<T> objs) {
        /**/
    }

    /**
     * On removed.
     * @param objs
     *            the objs
     */
    default void onRemoved(final Collection<T> objs) {/**/
    }

    /**
     * On edited.
     * @param obj
     *            the obj
     */
    default void onEdited(final T obj) {
        /**/
    }
}
