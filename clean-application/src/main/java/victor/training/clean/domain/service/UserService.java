package victor.training.clean.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.clean.infra.LdapApi;
import victor.training.clean.infra.LdapUserDto;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
   private final LdapApi ldapApi;

   public void importUserFromLdap(String username) {
      List<LdapUserDto> list = ldapApi.searchUsingGET(null, null, username.toUpperCase());

      if (list.size() != 1) {
         throw new IllegalArgumentException("There is no single user matching username " + username);
      }

      LdapUserDto dto = list.get(0);

      deepDomainLogic(dto);
   }

   private void deepDomainLogic(LdapUserDto dto) { // an object enters my domain logic with too much data
      if (dto.getWorkEmail()!=null) { // null in fields. NO i want optional !
         log.debug("Send welcome email to  " + dto.getWorkEmail()); // "work" is redundant
      }

      log.debug("Insert user in my database: " + dto.getUid()); // bad name "username"

      String fullName = dto.getFname() + " " + dto.getLname().toUpperCase(); // i used a derived value from the original fieelds
      innocentHack(dto); // mutability opens the door to GARBAGE
      log.debug("More business logic with " + fullName + " of id " + dto.getUid().toLowerCase());

      // then, in multiple places:
      sendMailTo(asContact(dto, fullName));
      sendMailTo(asContact(dto, fullName));
      sendMailTo(asContact(dto, fullName));
      sendMailTo(asContact(dto, fullName));
   }


   // the next big util tomorow: UserUtil
   private static String asContact(LdapUserDto dto, String fullName) {
      return fullName + " <" + dto.getWorkEmail() + ">";
   }

   private void innocentHack(LdapUserDto dto) {
      if (dto.getUid() == null) {
         dto.setUid("anonymous");
      }
   }

   private void sendMailTo(String emailContact) {
      System.out.println("Contact: " + emailContact);
      //..implem left out
   }

}
