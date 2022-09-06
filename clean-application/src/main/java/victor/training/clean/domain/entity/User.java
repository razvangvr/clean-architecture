package victor.training.clean.domain.entity;

import static java.util.Objects.requireNonNull;

// Value Object MINE! All Mine,,... .My Preciouis.....
public class User {
	private final String username;
	private final String fullName;
	private final String workEmail;

	public User(String username, String fullName, String workEmail) {
		this.username = requireNonNull(username);
		this.fullName = fullName;
		this.workEmail = workEmail;
	}


	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public boolean hasWorkEmail() {
		return getWorkEmail() != null;
	}
}
