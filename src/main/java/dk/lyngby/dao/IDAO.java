package dk.lyngby.dao;

import java.util.List;
import java.util.Set;

public interface IDAO<T> {
    void create(T t);
    T getById(long id);
    List<T> getAll();
    void update(T t,T newT);
    void delete(long id);


}
