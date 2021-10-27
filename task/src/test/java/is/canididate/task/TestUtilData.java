package is.canididate.task;

import is.canididate.task.model.entities.*;

public class TestUtilData {
    public static final String FULL_NAME = "Благо Благоев";

    public static final String PIN = "2121212121";

    public static final String EMAIL_TYPE = "T1";

    public static final String EMAIL_TYPE_NEW = "T2";

    public static final String EMAIL = "test_email@gmail.com";

    public static final String ADDRESS_TYPE = "T1";

    public static final String ADDRESS_TYPE_NEW = "T2";

    public static final String ADDRESS_INFO = "Адрес информация";

    public static People createPeople() {
        People people = new People();
        people.setFullName(TestUtilData.FULL_NAME);
        people.setPin(TestUtilData.PIN);
        return people;
    }

    public static Mail createMail() {
        Mail mail = new Mail();
        MailType mailType = new MailType();
        mailType.setEmailTypeCode(EMAIL_TYPE);
        mail.setEmailType(mailType);
        mail.setEmail(EMAIL);
        return mail;
    }

    public static Address createAddress() {
        Address address = new Address();
        AddressType addressType = new AddressType();
        addressType.setAddressTypeCode(ADDRESS_TYPE);
        address.setAddressType(addressType);
        address.setAddressInfo(ADDRESS_INFO);
        return address;
    }
}
