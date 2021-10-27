package is.canididate.task.services;

import is.canididate.task.model.DAOs.AddressDao;
import is.canididate.task.model.DAOs.AddressTypeDao;
import is.canididate.task.model.entities.Address;
import is.canididate.task.model.entities.AddressType;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressDao addressDao;

    private final AddressTypeDao addressTypeDao;

    public AddressService(AddressDao addressDao, AddressTypeDao addressTypeDao) {
        this.addressDao = addressDao;
        this.addressTypeDao = addressTypeDao;
    }

    public Address createOrUpdateAddress(Address address) {
        if (address == null) {
            return null;
        }

        return addressDao.save(address);
    }

    public List<AddressType> getAllAddressTypes() {
        return Lists.newArrayList(addressTypeDao.findAll());
    }

    public void deleteAddressById(Long addressId) {
        if (addressId != null) {
            addressDao.deleteById(addressId);
        }
    }
}
