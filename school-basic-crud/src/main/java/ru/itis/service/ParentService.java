package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.JpaEntityDao;
import ru.itis.exception.ResourceNotFoundException;
import ru.itis.model.Parent;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ParentService implements EntityCrudService<Parent, UUID>{

    private final JpaEntityDao<Parent, UUID> parentDao;
    @Override
    public Parent findById(UUID uuid) {
        return parentDao.findById(Parent.class, uuid).orElseThrow(() -> new ResourceNotFoundException(uuid));
    }

    @Override
    public List<Parent> findAll() {
        return parentDao.findAll(Parent.class);
    }

    @Override
    public Parent createEntity(Parent entity) {
        return parentDao.save(Parent.class,entity);
    }

    @Override
    public Parent updateEntity(Parent entity) {
        Parent parent = findById(entity.getId());

        parent.setName(entity.getName());

        return parentDao.save(Parent.class, parent);
    }

    @Override
    public void deleteEntity(UUID uuid) {
        Parent parent = findById(uuid);

        parentDao.delete(parent);
    }
}
