package victor.training.clean.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.clean.insurance.entity.InsurancePolicy;

public interface InsurancePolicyRepo extends JpaRepository<InsurancePolicy, Long> {
}