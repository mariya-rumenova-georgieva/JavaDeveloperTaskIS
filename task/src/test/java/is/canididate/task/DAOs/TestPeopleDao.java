package is.canididate.task.DAOs;


import is.canididate.task.TestUtilData;
import is.canididate.task.model.DAOs.PeopleDao;
import is.canididate.task.model.entities.People;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TestPeopleDao {

    @Autowired
    private PeopleDao peopleDao;

    private People people;

    private Long peopleId;

    @BeforeAll
    void initObjects() {
        people = TestUtilData.createPeople();
    }

    @Test
    public void testBaseCrudOperationsForPeople() {
        try {
            //Check if the object is saved in DB
            long countOfPeoples = peopleDao.count();
            People savedPeople = peopleDao.save(people);
            Assert.assertEquals(++countOfPeoples, peopleDao.count());

            // Get id from the saved object
            peopleId = savedPeople.getId();

            // Check if the saved object exists in DB
            People foundPeople = peopleDao.findById(peopleId).get();
            Assert.assertTrue(foundPeople.getId() == peopleId);

            Assert.assertTrue(peopleDao.existsById(peopleId));

            Assert.assertEquals(savedPeople.getFullName(), TestUtilData.FULL_NAME);
            // Update people object in DB
            String newPeopleName = TestUtilData.FULL_NAME + " NEW";
            savedPeople.setFullName(newPeopleName);
            People updatedPeople = peopleDao.save(savedPeople);
            Assert.assertEquals(updatedPeople.getFullName(), newPeopleName);
            Assert.assertEquals(updatedPeople.getId(), savedPeople.getId());

            // Delete people object from DB
            countOfPeoples = peopleDao.count();
            peopleDao.deleteById(peopleId);
            Assert.assertEquals(--countOfPeoples, peopleDao.count());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
