package victor.training.clean.infra;

import lombok.RequiredArgsConstructor;
import victor.training.clean.common.Adapter;
import victor.training.clean.domain.model.User;
import victor.training.clean.domain.service.ExternalUserProvider;

import java.util.List;

@Adapter
@RequiredArgsConstructor
public class LdapServiceAdapter implements ExternalUserProvider {
    private final LdapApi ldapApi;
    @Override
    public User findUserByUsername(String username) {
        List<LdapUserDto> list = ldapApi.searchUsingGET(null, null, username.toUpperCase());

        if (list.size() != 1) {
            throw new IllegalArgumentException("There is no single user matching username " + username);
        }

        LdapUserDto ldapUser = list.get(0);
        if (ldapUser.getFname() == null) {
            ldapUser.setFname("N/A");
        }
        String corporateName = ldapUser.getFname() + " " + ldapUser.getLname().toUpperCase();
        return new User(ldapUser.getUid(), ldapUser.getWorkEmail(), corporateName);
    }
}
