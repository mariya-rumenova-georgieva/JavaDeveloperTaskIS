package is.canididate.task.services;

import is.canididate.task.model.DAOs.PeopleDao;
import is.canididate.task.model.entities.People;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {
    private final PeopleDao peopleDao;

    public PeopleService(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    public People findPeople(Long peopleId) {
        return peopleId == null ? null: peopleDao.findById(peopleId).orElse(null);
    }

    public List<People> findAllPeoples() {
        return Lists.newArrayList(peopleDao.findAllPeoples());
    }

    public List<People> findAllPeoplesByName(String name) {
        if (name == null) {
            new ArrayList<>();
        }
        return Lists.newArrayList(peopleDao.findAllPeoplesByName(name));
    }

    public People createOrUpdatePeople(People people) {
        if (people == null) {
            return null;
        }

        People savedPeople = peopleDao.save(people);
        return savedPeople;
    }

    public void deletePeopleById(Long peopleId) {
        if (peopleId != null) {
            peopleDao.deleteById(peopleId);
        }
    }
}
