package ru.itis.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Repository
@AllArgsConstructor
public class JpaEntityDao<T, UUID> implements EntityDao<T, UUID>{

    private final SessionFactory sessionFactory;


    @Override
    public UUID save(T entity){
        UUID uuid ;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            uuid = (UUID) session.save(entity);

            session.getTransaction().commit();
            session.close();
        }
        return uuid;
    }

    @Override
    public T getEntityById(Class clazz, UUID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            T entity = (T) session.find(clazz, id);

            session.getTransaction().commit();
            session.close();
            return entity;
        }
    }

    @Override
    public List<T> getEntities(Class clazz) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<T> entities = session.createCriteria(clazz).list();

            session.getTransaction().commit();
            session.close();
            return entities;
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.remove(entity);

            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.update(entity);

            session.getTransaction().commit();
            session.close();
        }
    }

}
