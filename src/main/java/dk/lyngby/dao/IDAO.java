package dk.lyngby.dao;

import java.util.List;
import java.util.Set;

public interface IDAO<T> {
    T create(T t);
    T getById(long id);
    List<T> getAll();
    T update(T t);
    T delete(T t);

    T deleteById(int id);
}
