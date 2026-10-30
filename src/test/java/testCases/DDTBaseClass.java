package testCases;

import java.io.FileInputStream;
import java.util.Properties;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

public class DDTBaseClass extends  BaseClass{
@BeforeMethod
@Parameters("browser")
public void setup(@Optional("chrome") String browser) throws Exception {

    // Load properties file
    FileInputStream file = new FileInputStream(
        ".\\src\\test\\resources\\config.properties"
    );

    configProperties = new Properties();
    configProperties.load(file);
    file.close();

    logger = LogManager.getLogger(this.getClass());

    switch (browser.toLowerCase()) {
        case "chrome":
            driver = new ChromeDriver();
            break;

        case "edge":
            driver = new EdgeDriver();
            break;

        case "firefox":
            driver = new FirefoxDriver();
            break;

        default:
            throw new IllegalArgumentException(
                "Invalid browser name: " + browser
            );
    }

    driver.manage().timeouts()
          .implicitlyWait(Duration.ofSeconds(10));

    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.manage().window().maximize();

    driver.get(configProperties.getProperty("appURL"));
}

@AfterMethod(alwaysRun = true)
public void tearDown() {
    if (driver != null) {
        driver.quit();
        driver = null;
    }
}
}
