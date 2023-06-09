package ru.itis.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.itis.model.Lesson;
import ru.itis.model.LessonStudentId;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskEntityDao<T, LessonStudentId> implements EntityDao<T, LessonStudentId> {
    private final SessionFactory sessionFactory;

    @Override
    public T save(Class clazz, T entity){
        LessonStudentId uuid ;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            uuid = (LessonStudentId) session.save(entity);

            session.getTransaction().commit();
            session.close();
            return findById(clazz, uuid).orElseThrow();
        }
    }

    @Override
    public Optional<T> findById(Class clazz, LessonStudentId id) {
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
