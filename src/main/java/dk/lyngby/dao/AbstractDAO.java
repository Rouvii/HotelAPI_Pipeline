package dk.lyngby.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Set;

/**
 * @author Rouvi
 */


public abstract class AbstractDAO<T> implements IDAO<T> {
@PersistenceContext
   private final EntityManagerFactory emf;
    private final Class<T> entityClass;


    public AbstractDAO(EntityManagerFactory emf, Class<T> entityClass) {
        this.emf = emf;
        this.entityClass = entityClass;
    }

    @Override
    public T create(T t) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            return t;
        }

    }

    @Override
    public T getById(long id) {
        try(var em = emf.createEntityManager()) {
            return em.find(entityClass, id);
        }

    }

    @Override
    public List<T> getAll() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t", entityClass).getResultList();
        }
    }

    @Override
    public T update(T t) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
           var updatedEntity = em.merge(t);
            em.getTransaction().commit();
            return updatedEntity;
        }
    }

    @Override
    public T delete(T t) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            return t;
        }
    }

    @Override
    public T deleteById(int id) {
        try(var em = emf.createEntityManager()) {
            var t = em.find(entityClass, id);
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            return t;
        }
    }



}
