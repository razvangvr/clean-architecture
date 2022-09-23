package victor.training.clean.infra;

import lombok.RequiredArgsConstructor;
import victor.training.clean.domain.model.User;
import victor.training.clean.domain.service.ExternalUserProvider;

import java.util.List;

@RequiredArgsConstructor
public class LdapApiAdapter implements ExternalUserProvider {
    private final LdapApi ldapApi;

    @Override
    public User fetchUserByUsername(String username) {
        List<LdapUserDto> list = ldapApi.searchUsingGET(null, null, username.toUpperCase());

        if (list.size() != 1) {
            throw new IllegalArgumentException("There is no single user matching username " + username);
        }

        LdapUserDto ldapUser = list.get(0);
        String corporateName = ldapUser.getFname() + " " + ldapUser.getLname().toUpperCase();
        User user = new User(ldapUser.getUid(), corporateName, ldapUser.getWorkEmail());
        return user;
    }
}