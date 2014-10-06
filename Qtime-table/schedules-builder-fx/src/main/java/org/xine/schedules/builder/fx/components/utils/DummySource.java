package org.xine.schedules.builder.fx.components.utils;

import java.util.ArrayList;
import java.util.List;

import org.xine.schedules.builder.fx.components.subjects.SubjectDataModel;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class DummySource.
 */
public class DummySource {

    /**
     * Builder subjects.
     * @param model
     *            the model
     */
    public static void builderSubjects(final SubjectDataModel model) {
        final List<Subject> ss = new ArrayList<>();

        Subject s = new Subject();
        s.setId(1);
        s.setName("Ana");
        ss.add(s);

        s = new Subject();
        s.setId(2);
        s.setName("Bruno");
        ss.add(s);

        s = new Subject();
        s.setId(3);
        s.setName("Cesar");
        ss.add(s);

        model.getSubject().addAll(ss);
    }

}
