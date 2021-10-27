package is.canididate.task.model.DAOs;

import is.canididate.task.model.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends CrudRepository<Address, Long> {

}