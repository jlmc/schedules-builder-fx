package org.xine.schedules.builder.fx.components.utils;

import java.util.ArrayList;
import java.util.List;

import org.xine.schedules.builder.fx.components.subjects.SubjectDataModel;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class DummySource.
 */
public final class DummySource {

    /**
     * Instantiates a new dummy source.
     */
    private DummySource() {
    }

    /**
     * Builder subjects.
     * @param model
     *            the model
     */
    public static void builderSubjects(final SubjectDataModel model) {
        final List<Subject> subjects = new ArrayList<>();

        Subject s = new Subject();
        s.setId(1);
        s.setName("Ana");
        subjects.add(s);

        s = new Subject();
        s.setId(2);
        s.setName("Bruno");
        subjects.add(s);

        s = new Subject();
        s.setId(3);
        s.setName("Cesar");
        subjects.add(s);

        model.getSubject().addAll(subjects);
    }

}
