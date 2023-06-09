package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.TaskEntityDao;
import ru.itis.exception.ResourceNotFoundException;
import ru.itis.model.LessonStudentId;
import ru.itis.model.Task;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskService implements EntityCrudService<Task, LessonStudentId>{

    private final TaskEntityDao<Task, LessonStudentId> taskDao;

    @Override
    public Task findById(LessonStudentId lessonStudentId) {
        return taskDao.findById(Task.class, lessonStudentId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll(Task.class);
    }

    @Override
    public Task createEntity(Task entity) {
        return taskDao.save(Task.class, entity);
    }

    @Override
    public Task updateEntity(Task entity) {
        return null;
    }

    @Override
    public void deleteEntity(LessonStudentId lessonStudentId) {}
}
