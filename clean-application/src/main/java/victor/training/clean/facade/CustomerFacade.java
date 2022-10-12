package victor.training.clean.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import victor.training.clean.common.Facade;
import victor.training.clean.domain.model.Customer;
import victor.training.clean.domain.model.Email;
import victor.training.clean.domain.service.RegisterCustomerService;
import victor.training.clean.facade.dto.CustomerDto;
import victor.training.clean.facade.dto.CustomerSearchCriteria;
import victor.training.clean.facade.dto.CustomerSearchResult;
import victor.training.clean.infra.EmailSender;
import victor.training.clean.domain.repo.CustomerRepo;
import victor.training.clean.repo.CustomerSearchRepo;
import victor.training.clean.domain.repo.SiteRepo;
import victor.training.clean.domain.service.QuotationService;

import java.util.List;

//@Service
@Facade
@Transactional // probably too broad for high-TPS systems
@RequiredArgsConstructor
public class CustomerFacade {
    private final CustomerRepo customerRepo;
    private final EmailSender emailSender;
    private final SiteRepo siteRepo;
    private final CustomerSearchRepo customerSearchRepo;
    private final QuotationService quotationService;
    private final RegisterCustomerService registerCustomerService;

    public List<CustomerSearchResult> search(CustomerSearchCriteria searchCriteria) {
        return customerSearchRepo.search(searchCriteria);
    }

    public CustomerDto findById(long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow();

        // TODO move mapping logic somewhere else
        // #1 in mapper (scris de mana, automapper)
        // #2 in ctor/fct static in DTO daca nu e generat
//       return customer.toDto(); + extension
        return CustomerDto.fromEntity(customer);
    }

    public void register(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setName(dto.getName());
        customer.setSite(siteRepo.getById(dto.getSiteId()));

        // TODO experiment all the ways to do validation
        if (customer.getName().length() < 5) {
            throw new IllegalArgumentException("Name too short");
        }

        registerCustomerService.register(customer);

        quotationService.quoteCustomer(customer);

        sendRegistrationEmail(customer.getEmail());
    }

    private void sendRegistrationEmail(String emailAddress) {
        System.out.println("Sending activation link via email to " + emailAddress);
        Email email = new Email();
        email.setFrom("noreply");
        email.setTo(emailAddress);
        email.setSubject("Welcome");
        email.setBody("You'll like it! Sincerely, Team");
        emailSender.sendEmail(email);
    }
}

//static class CustomerExtensions {
//    public static CustomerDto toDto(this Customer customer) {
//        return CustomerDto.builder()
//                .id(customer.getId())
//                .name(customer.getName())
//                .email(customer.getEmail())
//                .siteId(customer.getSite().getId())
//                .creationDateStr(customer.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .build();
//    }
//}
