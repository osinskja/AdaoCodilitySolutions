package repository;

import repository.template.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class AbstractBaseRepository<T> implements BaseRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<T> findAll() {
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

    @Override
    public void deleteEntity(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    protected abstract Class<T> getEntityType();
}
