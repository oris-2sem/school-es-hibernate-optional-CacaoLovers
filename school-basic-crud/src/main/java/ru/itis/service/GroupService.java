package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.JpaEntityDao;
import ru.itis.exception.ResourceNotFoundException;
import ru.itis.model.Group;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService implements EntityCrudService<Group, UUID>{

    private final JpaEntityDao<Group, UUID> groupDao;

    @Override
    public Group findById(UUID uuid) {
        return groupDao.findById(Group.class, uuid).orElseThrow(() -> new ResourceNotFoundException(uuid));
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll(Group.class);
    }

    @Override
    public Group createEntity(Group entity) {
        return groupDao.save(Group.class, entity);
    }

    @Override
    public Group updateEntity(Group entity) {
        Group groupPersist = findById(entity.getId());

        groupPersist.setCourse(entity.getCourse());
        groupPersist.setYearOfGraduation(entity.getYearOfGraduation());
        groupPersist.setRating(entity.getRating());

        return groupDao.save(Group.class, groupPersist);
    }

    @Override
    public void deleteEntity(UUID uuid) {
        Group group = findById(uuid);
        groupDao.delete(group);
    }
}
