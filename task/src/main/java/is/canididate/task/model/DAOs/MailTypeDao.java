package is.canididate.task.model.DAOs;

import is.canididate.task.model.entities.MailType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailTypeDao extends CrudRepository<MailType, String> {
}