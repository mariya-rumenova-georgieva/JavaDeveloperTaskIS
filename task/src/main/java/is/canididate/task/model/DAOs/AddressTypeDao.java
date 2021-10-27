package is.canididate.task.model.DAOs;

import is.canididate.task.model.entities.AddressType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressTypeDao extends CrudRepository<AddressType, String> {

}