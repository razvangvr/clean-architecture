package victor.training.clean.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import victor.training.clean.domain.model.User;
import victor.training.clean.infra.LdapApi;
import victor.training.clean.infra.LdapUserDto;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class LdapApiAdapter {
  private final LdapApi ldapApi;

  public User fetchByUsername(String username) {
    List<LdapUserDto> list = ldapApi.searchUsingGET(null, null, username.toUpperCase());
    if (list.size() != 1) {
      throw new IllegalArgumentException("There is no single user matching username " + username);
    }
    LdapUserDto ldapUser = list.get(0);

    String fullName = ldapUser.getFname() + " " + ldapUser.getLname().toUpperCase(); // not here. The way i se the user's name
    User user = new User(ldapUser.getUid()==null?"N/A": ldapUser.getUid(), ldapUser.getWorkEmail(), fullName);
    return user;
  }
}