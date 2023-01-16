package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void successLogin() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("vladimirozz@gmail.com", "Vova1234$");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkMessage(),"Logged in success");
    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("vladimirozzgmail.com", "Vova1234$");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkWrongEmail(),"It'snot look like email");
    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("vladimirozz@gmail.com", "vova1234$");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkMessage(),"\"Login or Password incorrect\"");
    }

    @Test
    public void loginUnregisterUser() {
        Random random = new Random();
        int i = random.nextInt(1000);

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("chupakabra"+i+"@gmail.ru", "Chupakabra123456@#");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().checkMessage(),"\"Login or Password incorrect\"");

    }
    @AfterMethod
    public void closeDialog(){
        app.getHelperUser().closeDialog();

    }
}