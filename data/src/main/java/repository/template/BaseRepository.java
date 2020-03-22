package repository.template;

public interface BaseRepository<T> {
    Iterable<T> findAll();

    T findOne(String id);

    T persistEntity(T entity);
}
