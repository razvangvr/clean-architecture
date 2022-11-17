package victor.training.clean.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import victor.training.clean.common.Adapter;
import victor.training.clean.infra.LdapApi;
import victor.training.clean.infra.LdapUserDto;

import java.util.List;

@Adapter@RequiredArgsConstructor
@Slf4j
public class LdapServiceAdapter {
  private final LdapApi ldapApi;

  public User fetchByUsername(String username) {
    List<LdapUserDto> list = ldapApi.searchUsingGET(null, null, username.toUpperCase());

    if (list.size() != 1) {
      throw new IllegalArgumentException("There is no single user matching username " + username);
    }

    LdapUserDto dto = list.get(0);

    String fullName = dto.getFname() + " " + dto.getLname().toUpperCase();
    User user = new User(dto.getUid(), dto.getWorkEmail(), fullName);
    return user;
  }

}
