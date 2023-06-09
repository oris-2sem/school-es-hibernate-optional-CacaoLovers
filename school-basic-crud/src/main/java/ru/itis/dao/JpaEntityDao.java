package ru.itis.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaEntityDao<T, UUID> implements EntityDao<T, UUID>{

    private final SessionFactory sessionFactory;


    @Override
    public T save(Class clazz, T entity){
        UUID uuid;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            uuid = (UUID) session.save(entity);

            session.getTransaction().commit();
            session.close();
            return findById(clazz, uuid).orElseThrow();
        }
    }

    @Override
    public Optional<T> findById(Class clazz, UUID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            T entity = (T) session.find(clazz, id);

            session.getTransaction().commit();
            session.close();
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public List<T> findAll(Class clazz) {
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


}
