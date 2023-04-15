package ru.itis.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.itis.model.Lesson;
import ru.itis.model.LessonStudentId;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskEntityDao<T, LessonStudentId> implements EntityDao<T, LessonStudentId> {
    private final SessionFactory sessionFactory;

    @Override
    public LessonStudentId save(T entity){
        LessonStudentId uuid ;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            uuid = (LessonStudentId) session.save(entity);

            session.getTransaction().commit();
            session.close();
        }
        return uuid;
    }

    @Override
    public T getEntityById(Class clazz, LessonStudentId id) {
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
