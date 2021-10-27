package is.canididate.task.controllers;

import is.canididate.task.controllers.formPOJOs.PeopleForm;
import is.canididate.task.model.entities.Address;
import is.canididate.task.model.entities.Mail;
import is.canididate.task.model.entities.People;
import is.canididate.task.services.AddressService;
import is.canididate.task.services.MailService;
import is.canididate.task.services.PeopleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class PeopleController {
    public static final String SAVED_SUCCESSFULLY = "Записът беше извършен успешно.";
    // Services
    @Autowired
    PeopleService peopleService;

    @Autowired
    AddressService addressService;

    @Autowired
    MailService mailService;

    // Model and View
    ModelAndView modelAndView;

    // Form
    PeopleForm peopleForm = new PeopleForm();

    @RequestMapping(value = {"people", "people/{clean}"}, method = RequestMethod.GET)
    public ModelAndView getPeopleData( @PathVariable(value="clean", required = false) String clean,
                                       HttpSession httpSession) {

        modelAndView = new ModelAndView("people");

        Long peopleId = null;
        // Cleaning up the people id from the session in case of clean up of the form fields/search
        if (clean != null) {
            peopleForm.setPeople(null);
        } else {
            peopleId = (Long) httpSession.getAttribute("savedPeopleId");
        }

        if (peopleId != null) {
            People foundPeople = peopleService.findPeople(peopleId);
            peopleForm.setPeople(peopleId != null ? foundPeople : new People());
            if (foundPeople.getAddress() != null) {
                httpSession.setAttribute("savedAddressId", foundPeople.getAddress().getId());
            }
            if (foundPeople.getMail() != null) {
                httpSession.setAttribute("savedMailId", foundPeople.getMail().getId());
            }
        }

        fillListsForForm(peopleForm);

        modelAndView.addObject("peopleForm", peopleForm);
        return modelAndView;
    }

    private void fillListsForForm(PeopleForm peopleForm) {
        peopleForm.setAllPeoples(peopleService.findAllPeoples());
        peopleForm.setAllAddressTypes(addressService.getAllAddressTypes());
        peopleForm.setAllMailTypes(mailService.getAllMailTypes());
    }

    @RequestMapping(value = "people", method = RequestMethod.POST)
    public ModelAndView createOrUpdatePeople(@Valid @ModelAttribute("peopleForm") PeopleForm peopleForm,
                                             BindingResult bindingResult,
                                             HttpSession httpSession,
                                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("people");

            fillListsForForm(peopleForm);

            modelAndView.addObject("peopleForm", peopleForm);
            return modelAndView;
        }

        // People
        People formPeople = peopleForm.getPeople();
        Long peopleId = (Long) httpSession.getAttribute("savedPeopleId");
        if (formPeople.getId() == null && peopleId != null) {
            formPeople.setId(peopleId);
        }

        //Adjust PIN value
        formPeople.setPin(adjustPinValue(formPeople.getPin()));
        // Avoid TransientPropertyValueException
        Address formAddress = formPeople.getAddress();
        Mail formMail = formPeople.getMail();
        formPeople.setMail(null);
        formPeople.setAddress(null);
        People savedPeople = peopleService.createOrUpdatePeople(formPeople);
        httpSession.setAttribute("savedPeopleId", savedPeople.getId());

        persistAddress(httpSession, formAddress, savedPeople);

        persistMail(httpSession, formMail, savedPeople);

        setSuccessMessage(redirectAttributes, SAVED_SUCCESSFULLY);

        modelAndView = new ModelAndView("redirect:/people");

        return modelAndView;
    }

    private void setSuccessMessage(RedirectAttributes redirectAttributes, String s) {
        redirectAttributes.addFlashAttribute("message", s);
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    }

    private void persistMail(HttpSession httpSession, Mail formMail, People savedPeople) {
        if (formMail != null) {
            formMail.setPeople(savedPeople);
            if (formMail.getId() == null) {
                Long mailId = (Long) httpSession.getAttribute("savedMailId");
                formMail.setId(mailId);
            }
            Mail savedMail = mailService.createOrUpdateMail(formMail);
            httpSession.setAttribute("savedMailId", savedMail.getId());
        }
    }

    private void persistAddress(HttpSession httpSession, Address formAddress, People savedPeople) {
        if (formAddress != null) {
            formAddress.setPeople(savedPeople);
            if (formAddress.getId() == null) {
                Long addressId = (Long) httpSession.getAttribute("savedAddressId");
                formAddress.setId(addressId);
            }
            Address savedAddress = addressService.createOrUpdateAddress(formAddress);
            httpSession.setAttribute("savedAddressId", savedAddress.getId());
        }
    }

    String adjustPinValue(String pin) {
        if (StringUtils.isBlank(pin)) {
            return null;
        }
        return StringUtils.leftPad(pin, 10, "0");
    }

    @RequestMapping(value = {"deletePeopleData"}, method = RequestMethod.POST)
    public ModelAndView deletePeopleData(@RequestParam(value = "peopleId", required = false) Long peopleId,
                                         HttpSession httpSession,
                                         RedirectAttributes redirectAttributes) {
        People foundPeople = peopleService.findPeople(peopleId);

        // Delete a address for the people if it exist
        Address address = foundPeople.getAddress();
        if (address != null && address.getId() != null) {
            addressService.deleteAddressById(address.getId());
        }

        // Delete a email for the people if it exist
        Mail mail = foundPeople.getMail();
        if (mail != null && mail.getId() != null) {
            mailService.deleteMailById(mail.getId());
        }

        // Delete selected people
        peopleService.deletePeopleById(peopleId);
        cleanUpSessionAtributes(httpSession);

        modelAndView = new ModelAndView("redirect:/people");
        return modelAndView;
    }

    private void cleanUpSessionAtributes(HttpSession httpSession) {
        httpSession.setAttribute("savedPeopleId", null);
        httpSession.setAttribute("savedAddressId", null);
        httpSession.setAttribute("savedMailId", null);
    }

    @RequestMapping(value = {"editPeopleData"}, method = RequestMethod.POST)
    public ModelAndView editPeopleData(@RequestParam(value = "peopleId", required = false) Long peopleId, HttpSession httpSession) {
        httpSession.setAttribute("savedPeopleId", peopleId);

        modelAndView = new ModelAndView("redirect:/people");
        return modelAndView;
    }

    @RequestMapping(value = {"searchPeople"}, method=RequestMethod.POST)
    public ModelAndView searchPeopleData(@RequestParam(value = "name", required = false) String name, HttpSession httpSession) {
        String trimmedName = name.trim();

        peopleForm.setAllPeoples(peopleService.findAllPeoplesByName(trimmedName));
        return modelAndView;
    }
}