package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import org.testng.Assert;
import utilities.DataProviders;
import pageObjects.LoginPage;

public class TC_LoginDDT002 extends DDTBaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DDT")
    public void verify_login_DDT(String email, String pwd, String exp) {
        logger.info("***Starting TC_LoginDDT002***");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(email);
        lp.setPassword(pwd);
        lp.Signin();

        String valurl = driver.getCurrentUrl();
        String dashboardUrl = "https://erp-qa.eagle.org/dashboard";

        if (exp.equalsIgnoreCase("Valid")) {

            wait.until(ExpectedConditions.urlToBe(dashboardUrl));
            valurl = driver.getCurrentUrl();

            Assert.assertEquals(valurl, dashboardUrl,
                    "Valid login failed");

            driver.findElement(
                    By.xpath("//span[contains(text(),'Sign out')]")).click();

            driver.findElement(
                    By.xpath("//button[contains(text(),'Sign out')]")).click();

            logger.info("***Test Passed: Logged successfully***");
        }

        if (exp.equalsIgnoreCase("InValid")) {

            Assert.assertNotEquals(valurl, dashboardUrl,
                    "Invalid credentials unexpectedly logged in");

            logger.info("***Test Passed: User could not log in with invalid credentials***");
        }
    }
}
