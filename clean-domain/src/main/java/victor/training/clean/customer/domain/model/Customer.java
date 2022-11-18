package victor.training.clean.customer.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data // Avoid on @Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	private LocalDate creationDate = LocalDate.now();
	private boolean goldMember;
	@ManyToOne
	private Site site;

  public int getDiscountPercentage() {
    int discountPercentage = 3;
    if (isGoldMember()) {
      discountPercentage += 1;
    }
    return discountPercentage;
  }
  //	@OneToMany
//	List<Phone>

}