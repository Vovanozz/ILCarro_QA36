
package manager;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public boolean isLogged() {
//
        List<WebElement> list  = wd.findElements(By.xpath("//a[text()=' Logout ']"));
        return list.size() > 0;
    }
    public void logout(){
        click(By.xpath("//a[text()=' Logout ']"));
    }
public void openLoginForm(){
        click(By.xpath("//a[text()=' Log in ']"));
}
public void fillLoginForm(String email,String password){
        type(By.xpath("//input[@id='email']"),email);
        type(By.xpath("//input[@id='password']"),password);

}

public void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
}
public void clickOkButton(){
        click(By.xpath("//button[@type='button']"));
}



}