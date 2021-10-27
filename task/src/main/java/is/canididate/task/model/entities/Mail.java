package is.canididate.task.model.entities;

import is.canididate.task.validators.ValidEmail;

import javax.persistence.*;

@Entity
@Table(name = "T_MAILS")
public class Mail {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "T_PEOPLE_ID", referencedColumnName = "ID")
    private People people;

    @ManyToOne
    @JoinColumn(name = "EMAIL_TYPE", referencedColumnName = "EMAIL_TYPE_CODE")
    private MailType emailType;

    @Column(name = "EMAIL")
    @ValidEmail
    private String email;

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

    public MailType getEmailType() {
        return emailType;
    }

    public void setEmailType(MailType emailType) {
        this.emailType = emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}