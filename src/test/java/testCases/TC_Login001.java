package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC_Login001 extends BaseClass {

    @Test(groups = "sanity")
    public void verify_login() {
        logger.info("***Starting TC_Login001***");

            LoginPage lp = new LoginPage(driver);
            lp.setUserName(configProperties.getProperty("username"));//reading username from properties file
            lp.setPassword(configProperties.getProperty("password"));//reading password from properties file
            lp.Signin();
            logger.info("Logged successfully..");

            wait.until(ExpectedConditions.urlToBe("https://erp-qa.eagle.org/dashboard"));

            String valurl = driver.getCurrentUrl();
            Assert.assertEquals(valurl, "https://erp-qa.eagle.org/dashboard");

            logger.info("TestCase Passed Succesfully..");
    }
}