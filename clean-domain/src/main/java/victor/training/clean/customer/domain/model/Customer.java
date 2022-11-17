package victor.training.clean.customer.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Setter// consider encapsulating changes
@Getter
@Entity
// Avoid on @Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Size(min = 5)
	private String name;
	private String email;
	private LocalDate creationDate = LocalDate.now();
	private boolean goldMember;
	@ManyToOne
	@Exclude
	private Site site;

//	protected Customer() {} // just for Hibernate <<-
//	public Customer(String name) {
//		if (name.length() < 5) {
//			throw new IllegalArgumentException();
//		}
//		this.name = name;
//	}

//	private List<Child> children;
//
//	public void method() {
//		CustomerDto
//	}
  public int getDiscountPercentage() {
      int discountPercentage = 3;
      if (isGoldMember()) {
          discountPercentage += 1;
      }
      return discountPercentage;
  }
}
//@Entity
//class Child {
//	@JsonIgnore. better: @JsonBackReference
//	Customer customer;
//}
