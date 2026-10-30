package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends  BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }

//Elements of the Page
@FindBy (xpath = "//input[@id='username']")
WebElement txtUserName;
@FindBy (xpath = "//input[@id='password']")
WebElement txtPassword;
@FindBy (xpath = "//button[contains(text(),'Sign in')]")
WebElement btnSignIn;

//Methods for the elements
public void setUserName(String username){
    txtUserName.sendKeys(username);
}
public void setPassword(String pwd){
    txtPassword.sendKeys(pwd);
}
public void Signin(){
    btnSignIn.click();
}
}
