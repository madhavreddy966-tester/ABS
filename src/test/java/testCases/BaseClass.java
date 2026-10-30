package testCases;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public WebDriverWait wait;
    public Properties configProperties;

    @BeforeClass (groups = {"sanity", "DDT"})
    @Parameters ("browser")
    public void setup(@Optional("chrome") String browser) throws Exception {
        //loading properties file
        FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.properties");
        configProperties = new Properties();
        configProperties.load(file);


        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()) {
            case "chrome": driver= new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver= new FirefoxDriver(); break;
        
            default: System.out.println("invalid browser name");return;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(configProperties.getProperty("appURL")); //reading URL from properties file
        driver.manage().window().maximize();
    }

    @AfterClass (groups = {"sanity", "DDT"})
    public void tearDown() {
        driver.quit();
    }

    public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
