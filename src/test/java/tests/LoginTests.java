package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
@BeforeMethod
public void preCondition(){
    if(app.getHelperUser().isLogged()){
       app.getHelperUser().logout();
    }

}

    @Test
    public void loginSuccess(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("vladimirozz@gmail.com","Vova1234$");
    app.getHelperUser().submitLogin();

    }

    @Test
    public void loginWrongEmail(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("vladimirozzgmail.com","Vova1234$");
    app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//div[text()=\\\"It'snot look like email\\\"]")));




    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("vladimirozz@gmail.com","vova1234$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//h1[normalize-space()='Login failed']")));
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//h2[normalize-space()='\"Login or Password incorrect\"']")));
    }
    @Test
    public void loginUnregisterUser(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("chupakabra@gmail.com","Chupakabra123456@#");
    app.getHelperUser().submitLogin();
    

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
