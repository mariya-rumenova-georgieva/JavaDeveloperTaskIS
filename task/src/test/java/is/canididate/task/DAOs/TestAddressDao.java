package is.canididate.task.DAOs;

import is.canididate.task.TestUtilData;
import is.canididate.task.model.DAOs.AddressDao;
import is.canididate.task.model.DAOs.PeopleDao;
import is.canididate.task.model.entities.Address;
import is.canididate.task.model.entities.AddressType;
import is.canididate.task.model.entities.People;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TestAddressDao {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private PeopleDao peopleDao;

    private Address address;

    private Long addressId;

    private People people;

    @BeforeAll
    void initObjects() {
        address = TestUtilData.createAddress();
        people = TestUtilData.createPeople();
        People savedPeople = peopleDao.save(people);
        address.setPeople(savedPeople);
    }

    @Test
    public void testBaseCrudOperationsForAddress() {
        try {
            //Check if the object is saved in DB
            long countOfAddresses = addressDao.count();
            Address savedAddress = addressDao.save(address);
            Assert.assertEquals(++countOfAddresses, addressDao.count());

            // Get id from the saved object
            addressId = savedAddress.getId();

            // Check if the saved object exists in DB
            Address foundAddress = addressDao.findById(addressId).get();
            Assert.assertTrue(foundAddress.getId() == addressId);

            Assert.assertTrue(addressDao.existsById(addressId));

            Assert.assertEquals(savedAddress.getAddressType().getAddressTypeCode(), TestUtilData.ADDRESS_TYPE);
            // Update address object in DB
            AddressType newAddressType = new AddressType();
            newAddressType.setAddressTypeCode(TestUtilData.ADDRESS_TYPE_NEW);
            savedAddress.setAddressType(newAddressType);
            Address updatedAddress = addressDao.save(savedAddress);
            Assert.assertEquals(updatedAddress.getAddressType().getAddressTypeCode(), TestUtilData.ADDRESS_TYPE_NEW);
            Assert.assertEquals(updatedAddress.getId(), savedAddress.getId());

            // Delete address object from DB
            countOfAddresses = addressDao.count();
            addressDao.deleteById(addressId);
            Assert.assertEquals(--countOfAddresses, addressDao.count());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
