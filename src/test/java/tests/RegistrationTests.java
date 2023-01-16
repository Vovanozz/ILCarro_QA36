package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void successRegistration()  {
        Random random = new Random();
        int i = random.nextInt(1000);

        User user = new User().withName("Vovan").withLastName("Ozer").withEmail("vov4ka"+i+"@mail.ru").withPassword("Vova1234$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkTermsOfUse();
        app.getHelperUser().checkPrivacyPolicy();
        app.getHelperUser().clickCheckbox();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void RegistrationWrongEmail() {
        User user = new User().withName("Vovan").withLastName("Ozer").withEmail("vov4kagmail.com").withPassword("Vovan1234$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkTermsOfUse();
        app.getHelperUser().checkPrivacyPolicy();
        app.getHelperUser().clickCheckbox();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkWrongEmail(),"Wrong email format");
        app.getHelperUser().isYallaButtonNotActive();

    }

    @Test
    public void RegistrationWrongPassword() {

        User user = new User().withName("Vovan").withLastName("Ozer").withEmail("vov4ka@gmail.com").withPassword("vov1234$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickAnyway();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkWrongPassword(),
                "Password must contain 1 uppercase letter, " +
                        "1 lowercase letter, " +
                        "1 number and one special symbol of [@$#^&*!]");
        app.getHelperUser().isYallaButtonNotActive();

    }

    @Test
    public void RegistrationRegisteredUser() {
        User user = new User().withName("Vovan").withLastName("Ozer").withEmail("vov4ka@gmail.com").withPassword("Vov1234$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkTermsOfUse();
        app.getHelperUser().checkPrivacyPolicy();
        app.getHelperUser().clickCheckbox();
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkMessage(),"\"User already exists\"");

    }
    @AfterMethod
    public void closeDialog() {
        app.getHelperUser().closeDialog();
    }
}