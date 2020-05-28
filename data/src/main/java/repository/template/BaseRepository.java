package repository.template;

import java.util.List;

public interface BaseRepository<T> {
    List<T> findAll();

    T findOne(String id);

    T persistEntity(T entity);

    void deleteEntity(T entity);
}
