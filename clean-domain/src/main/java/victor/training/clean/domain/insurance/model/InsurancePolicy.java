package victor.training.clean.domain.insurance.model;

import lombok.Getter;
import lombok.Setter;
import victor.training.clean.domain.customer.model.Customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class InsurancePolicy {
   @Id
   @GeneratedValue
   private Long id;

   @ManyToOne
   private Customer customer;

   private BigDecimal valueInEur;


}