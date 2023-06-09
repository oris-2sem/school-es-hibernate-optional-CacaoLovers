package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.JpaEntityDao;
import ru.itis.exception.ResourceNotFoundException;
import ru.itis.model.Student;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StudentService implements EntityCrudService<Student, UUID>{

    private final JpaEntityDao<Student, UUID> studentDao;
    @Override
    public Student findById(UUID uuid) {
        return studentDao.findById(Student.class, uuid).orElseThrow(() -> new ResourceNotFoundException(uuid));
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll(Student.class);
    }

    @Override
    public Student createEntity(Student entity) {
        return studentDao.save(Student.class, entity);
    }

    @Override
    public Student updateEntity(Student entity) {
        Student student = findById(entity.getId());

        student.setGroup(entity.getGroup());
        student.setName(entity.getName());
        student.setRating(entity.getRating());

        return studentDao.save(Student.class, student);
    }

    @Override
    public void deleteEntity(UUID uuid) {
        Student student = findById(uuid);

        studentDao.delete(student);
    }
}
