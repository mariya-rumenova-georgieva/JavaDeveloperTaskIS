package is.canididate.task.model.entities;

import is.canididate.task.validators.ValidFullName;
import is.canididate.task.validators.ValidPin;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "T_PEOPLE")
public class People {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULL_NAME")
    @ValidFullName
    private String fullName;

    @Column(name = "PIN")
    @ValidPin
    private String pin;

    @OneToOne(mappedBy = "people")
    @Valid
    private Mail mail;

    @OneToOne(mappedBy = "people")
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}