package org.xine.qtime.server.rest.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

import org.xine.qtime.server.rest.entities.Subject;


/**
 * The Class SubjectDaoImpl.
 */
@ApplicationScoped
public class SubjectDaoImpl implements SubjectDao{
  
  /** The Constant subjects. */
  private static final List<Subject> subjects = Collections.synchronizedList(
      new ArrayList<Subject>(
          Arrays.asList(
              new Subject[]{
                  new Subject(1L, "Aa", "a", "a A a"), 
                  new Subject(2L, "Bb", "b", "b B b")})));
  
  
  private static AtomicLong conter = new AtomicLong(3L);
  
  /* (non-Javadoc)
   * @see org.xine.qtime.server.rest.services.Dao#list()
   */
  public List<Subject> list(){
    return new ArrayList<Subject>(subjects);
  }




  /* (non-Javadoc)
   * @see org.xine.qtime.server.rest.services.Dao#save(java.lang.Object)
   */
  @Override
  public Subject save(Subject entity) {
    if(entity != null){
      entity.setId(conter.incrementAndGet());
      if(subjects.add(entity))
        return entity;
    }
    throw new RuntimeException("invalid entity");
  }




  /* (non-Javadoc)
   * @see org.xine.qtime.server.rest.services.Dao#read(java.lang.Long)
   */
  @Override
  public Subject read(Long id) {
    if(id != null){
      for (Subject subject : subjects) {
        if(subject.getId().equals(id))
          return subject;
      }
    }
    return null;
  }




  @Override
  public void delete(Subject entity) {
    if(entity == null || entity.getId() == null){
      throw new RuntimeException("invalid entity");
    }
    
    Subject s = null;
    for (Subject subject : subjects) {
      if(subject.getId().equals(entity.getId()))
        s = subject;
    }
    
    if(s != null)
      subjects.remove(s);
    else
      throw new RuntimeException("entity no exists on System");
  }




  @Override
  public Subject update(Subject entity) {
    if(entity != null){
       for (Subject subject : subjects) {
        if(subject.getId().equals(entity.getId())){
          subject.setName(entity.getName());
          subject.setDescription(entity.getDescription());
          subject.setAcronym(entity.getAcronym());
          return subject;
        }
      }
    }
    throw new RuntimeException("invalid entity");
  }




  
  
  
   

}
