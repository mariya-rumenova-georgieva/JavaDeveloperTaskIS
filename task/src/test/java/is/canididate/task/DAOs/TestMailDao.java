package is.canididate.task.DAOs;


import is.canididate.task.TestUtilData;
import is.canididate.task.model.DAOs.MailDao;
import is.canididate.task.model.DAOs.PeopleDao;
import is.canididate.task.model.entities.Mail;
import is.canididate.task.model.entities.MailType;
import is.canididate.task.model.entities.People;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TestMailDao {

    @Autowired
    private MailDao mailDao;

    @Autowired
    private PeopleDao peopleDao;

    private Mail mail;

    private Long mailId;

    private People people;

    @BeforeAll
    void initObjects() {
        mail = TestUtilData.createMail();
        people = TestUtilData.createPeople();
        People savedPeople = peopleDao.save(people);
        mail.setPeople(savedPeople);
    }

    @Test
    public void testBaseCrudOperationsForMail() {
        try {
            //Check if the object is saved in DB
            long countOfMails = mailDao.count();
            Mail savedMail = mailDao.save(mail);
            Assert.assertEquals(++countOfMails, mailDao.count());

            // Get id from the saved object
            mailId = savedMail.getId();

            // Check if the saved object exists in DB
            Mail foundMail = mailDao.findById(mailId).get();
            Assert.assertTrue(foundMail.getId() == mailId);

            Assert.assertTrue(mailDao.existsById(mailId));

            Assert.assertEquals(savedMail.getEmailType().getEmailTypeCode(), TestUtilData.EMAIL_TYPE);
            // Update mail object in DB
            MailType newEmailType = new MailType();
            newEmailType.setEmailTypeCode(TestUtilData.EMAIL_TYPE_NEW);
            savedMail.setEmailType(newEmailType);
            Mail updatedMail = mailDao.save(savedMail);
            Assert.assertEquals(updatedMail.getEmailType().getEmailTypeCode(), TestUtilData.EMAIL_TYPE_NEW);
            Assert.assertEquals(updatedMail.getId(), savedMail.getId());

            // Delete mail object from DB
            countOfMails = mailDao.count();

            mailDao.delete(foundMail);
            Assert.assertEquals(--countOfMails, mailDao.count());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
