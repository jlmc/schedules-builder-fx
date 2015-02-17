package org.xine.qtime.dal.core.daos;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, ID extends Serializable> {

    T save(T entity);

    T update(T entity);

    T read(ID id);

    List<T> list();

    void delete(T entity);

}
