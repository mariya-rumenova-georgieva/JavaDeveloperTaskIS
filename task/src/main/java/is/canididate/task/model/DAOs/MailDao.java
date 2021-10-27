package is.canididate.task.model.DAOs;

import is.canididate.task.model.entities.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailDao extends CrudRepository<Mail, Long> {
}