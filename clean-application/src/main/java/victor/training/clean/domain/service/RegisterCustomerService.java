package victor.training.clean.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.clean.domain.model.Customer;
import victor.training.clean.domain.repo.CustomerRepo;

@RequiredArgsConstructor
@Service
public class RegisterCustomerService {
    private final CustomerRepo customerRepo;

//    public void oups(CustomerDto customerDto) {
//    }
    public void register(Customer customer) {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Customer email is already registered");
            // throw new CleanException(ErrorCode.DUPLICATED_CUSTOMER_EMAIL);
        }

        // Heavy business logic
        // Heavy business logic
        // Heavy business logic
        // TODO Where can I move this little logic? (... operating on the state of a single entity)
        int discountPercentage = 3;
        if (customer.isGoldMember()) {
            discountPercentage += 1;
        }
        System.out.println("Biz Logic with discount " + discountPercentage);
        // Heavy business logic
        // Heavy business logic
        customerRepo.save(customer);
        // Heavy business logic
    }
}
