package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.JpaEntityDao;
import ru.itis.exception.ResourceNotFoundException;
import ru.itis.model.Lesson;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService implements EntityCrudService<Lesson, UUID>{

    private final JpaEntityDao<Lesson, UUID> lessonDao;
    @Override
    public Lesson findById(UUID uuid) {
        return lessonDao.findById(Lesson.class, uuid).orElseThrow(() -> new ResourceNotFoundException(uuid));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonDao.findAll(Lesson.class);
    }

    @Override
    public Lesson createEntity(Lesson entity) {
        return lessonDao.save(Lesson.class, entity);
    }

    @Override
    public Lesson updateEntity(Lesson entity) {
        Lesson lesson = findById(entity.getId());

        lesson.setSubject(entity.getSubject());
        lesson.setTheme(entity.getTheme());
        lesson.setTimetable(entity.getTimetable());

        return lessonDao.save(Lesson.class, lesson);
    }

    @Override
    public void deleteEntity(UUID uuid) {
        Lesson lesson = findById(uuid);

        lessonDao.delete(lesson);
    }
}
