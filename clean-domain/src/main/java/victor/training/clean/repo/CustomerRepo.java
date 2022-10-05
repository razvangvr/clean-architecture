package victor.training.clean.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.clean.domain.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	Customer getCustomerByEmail(String email);

	boolean existsByEmail(String email);

}
