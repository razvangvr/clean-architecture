package victor.training.clean.domain.insurance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.clean.domain.customer.model.Customer;
import victor.training.clean.domain.insurance.model.InsurancePolicy;
import victor.training.clean.domain.customer.repo.CustomerRepo;
import victor.training.clean.domain.insurance.repo.InsurancePolicyRepo;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuotationService {
   private final InsurancePolicyRepo insurancePolicyRepo;

   public void quoteCustomer(Long customerId, String customerName) {
      log.debug("Quoting customer (~230 total lines of code, 40 Cyclomatic Complexity): " + customerId);
      InsurancePolicy policy = new InsurancePolicy();
      policy.setCustomerId(customerId);
      policy.setCustomerName(customerName);
      policy.setValueInEur(BigDecimal.ONE);
      insurancePolicyRepo.save(policy);
   }

   public void printPolicy(long policyId) {
      InsurancePolicy policy = insurancePolicyRepo.findById(policyId).orElseThrow();
//      String customerName = customerRepo.findById(policy.getCustomerId()).orElseThrow().getName();
      System.out.println("Insurange Policy for " + policy.getCustomerName());

   }
}
