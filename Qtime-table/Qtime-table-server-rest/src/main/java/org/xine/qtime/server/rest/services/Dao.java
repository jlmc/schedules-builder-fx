package org.xine.qtime.server.rest.services;

import java.util.List;

public interface Dao<T> {

  List<T> list();
  
  T save(T entity);
  
  T read(Long id);
  
  T update(T entity);
  
  void delete(T entity);
  
  
  
  
}
