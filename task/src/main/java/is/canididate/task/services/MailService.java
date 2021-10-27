package is.canididate.task.services;

import is.canididate.task.model.DAOs.MailDao;
import is.canididate.task.model.DAOs.MailTypeDao;
import is.canididate.task.model.entities.Mail;
import is.canididate.task.model.entities.MailType;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    private final MailDao mailDao;

    private final MailTypeDao mailTypeDao;

    public MailService(MailDao mailDao, MailTypeDao mailTypeDao) {
        this.mailDao = mailDao;
        this.mailTypeDao = mailTypeDao;
    }

    public Mail createOrUpdateMail(Mail mail) {
        if (mail == null) {
            return null;
        }

        return mailDao.save(mail);
    }

    public List<MailType> getAllMailTypes() {
        return Lists.newArrayList(mailTypeDao.findAll());
    }


    public void deleteMailById(Long mailId) {
        if (mailId != null) {
            mailDao.deleteById(mailId);
        }
    }
}
