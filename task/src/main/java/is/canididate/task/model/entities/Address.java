package is.canididate.task.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "T_ADDRESSES")
public class Address {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "T_PEOPLE_ID", referencedColumnName = "ID")
    private People people;

    @ManyToOne
    @JoinColumn(name = "ADDR_TYPE", referencedColumnName = "ADDRESS_TYPE_CODE")
    private AddressType addressType;

    @Column(name = "ADDR_INFO")
    private String addressInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}