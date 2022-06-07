package victor.training.clean.domain.service;

import victor.training.clean.domain.entity.User;
import victor.training.clean.infra.LdapUserDto;

public interface ExternalUserService {
    LdapUserDto shitToTheDOmain();

    User getUserByUsername(String username);
}
