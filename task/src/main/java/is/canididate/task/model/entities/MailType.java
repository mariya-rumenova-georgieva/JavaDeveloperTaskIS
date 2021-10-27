package is.canididate.task.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_EMAIL_TYPE")
public class MailType {
    @Id
    @Column(name = "EMAIL_TYPE_CODE")
    private String emailTypeCode;

    @Column(name = "EMAIL_TYPE_NAME")
    private String emailTypeName;

    @OneToMany(mappedBy = "emailType")
    private List<Mail> mails;

    public String getEmailTypeCode() {
        return emailTypeCode;
    }

    public void setEmailTypeCode(String emailTypeCode) {
        this.emailTypeCode = emailTypeCode;
    }

    public String getEmailTypeName() {
        return emailTypeName;
    }

    public void setEmailTypeName(String emailTypeName) {
        this.emailTypeName = emailTypeName;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }
}