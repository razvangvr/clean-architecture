package victor.training.clean.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.clean.domain.model.Email;
import victor.training.clean.infra.EmailSender;

@RequiredArgsConstructor
@Service
public class NotificationService {
  private final EmailSender emailSender;

  public void sendRegistrationEmail(String emailAddress) {
    System.out.println("Sending activation link via email to " + emailAddress);
    Email email = new Email();
    email.setFrom("noreply");
    email.setTo(emailAddress);
    email.setSubject("Welcome");
    email.setBody("You'll like it! Sincerely, Team");
    emailSender.sendEmail(email);
  }
}