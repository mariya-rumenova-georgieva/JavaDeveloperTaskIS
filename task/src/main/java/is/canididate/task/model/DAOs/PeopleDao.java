package is.canididate.task.model.DAOs;

import is.canididate.task.model.entities.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleDao extends CrudRepository<People, Long>{

    @Query(value = "select * from T_PEOPLE as p left join T_ADDRESSES as a on p.ID = a.T_PEOPLE_ID " +
            "left join T_MAILS as m on p.ID = m.T_PEOPLE_ID ", nativeQuery = true)
    Iterable<People> findAllPeoples();

    @Query(value = "select * from T_PEOPLE as p left join T_ADDRESSES as a on p.ID = a.T_PEOPLE_ID " +
            "left join T_MAILS as m on p.ID = m.T_PEOPLE_ID " +
            "where p.FULL_NAME like %:name%", nativeQuery = true)
    Iterable<People> findAllPeoplesByName(@Param("name") String name);
}