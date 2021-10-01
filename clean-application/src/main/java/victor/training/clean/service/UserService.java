package victor.training.clean.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.clean.entity.User;
import victor.training.clean.infra.LdapUserDto;
import victor.training.clean.infra.LdapUserWebserviceClient;

import java.util.List;

@Slf4j
@Service
public class UserService {
	@Autowired
	private LdapUserWebserviceClient wsClient;

	public void importUserFromLdap(String username) {
		List<LdapUserDto> list = searchByUsername(username);
		if (list.size() != 1) {
			throw new IllegalArgumentException("There is no single user matching username " + username);
		}
		User user = fromDto(list.get(0));

		if (user.getWorkEmail() != null) {
			log.debug("Send welcome email to " + user.getWorkEmail());
		}
		log.debug("Insert user in my database");
	}

	private User fromDto(LdapUserDto dto) {
		String fullName = dto.getfName() + " " + dto.getlName().toUpperCase();
		return new User(dto.getuId(), fullName, dto.getWorkEmail());
	}


	private List<LdapUserDto> searchByUsername(String username) {
		return wsClient.search(username.toUpperCase(), null, null);
	}


}
