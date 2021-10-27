package is.canididate.task.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_ADDRESS_TYPE")
public class AddressType {
    @Id
    @Column(name = "ADDRESS_TYPE_CODE")
    private String addressTypeCode;

    @Column(name = "ADDRESS_TYPE_NAME")
    private String addressTypeName;

    @OneToMany(mappedBy = "addressType")
    private List<Address> addresses;

    public String getAddressTypeCode() {
        return addressTypeCode;
    }

    public void setAddressTypeCode(String addressTypeCode) {
        this.addressTypeCode = addressTypeCode;
    }

    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}