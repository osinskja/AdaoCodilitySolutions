package repository;

import repository.template.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;

public abstract class AbstractBaseRepository<T> implements BaseRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + getEntityType().getSimpleName() + " e" , getEntityType());
        return query.getResultList();
    }

    @Override
    public T findOne(String id) {
        return entityManager.find(getEntityType(), id);
    }

    @Override
    public T persistEntity(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    protected abstract Class<T> getEntityType();
}
