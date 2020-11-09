package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.mantis.tests.RegistrationTests.findConfirmationLink;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws InterruptedException, IOException {
    UserData user = app.db().users().iterator().next();
    app.session().loginAsAdminUI();
    app.user().selectUserForChange(user.getId());
    app.user().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    String newpassword = app.user().modifyPassword(confirmationLink, user.getUsername());
    assertTrue(app.newSession().login(user.getUsername(), newpassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

