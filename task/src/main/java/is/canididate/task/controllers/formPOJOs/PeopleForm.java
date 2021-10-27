package is.canididate.task.controllers.formPOJOs;

import is.canididate.task.model.entities.AddressType;
import is.canididate.task.model.entities.MailType;
import is.canididate.task.model.entities.People;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class PeopleForm {
    @Valid
    private People people;

    private List<People> allPeoples;

    private String name;

    private List<MailType> allMailTypes;

    private List<AddressType> allAddressTypes;
}
