package Controls;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Hooks {
        public WebDriver driver;
        @BeforeTest
        public void setup() throws IOException {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/properties/config.properties");
            properties.load(file);

            String browserType = properties.getProperty("browser").toLowerCase().trim();
            String url = properties.getProperty("defaultUrl").trim();





            switch (browserType){

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    driver = new EdgeDriver(edgeOptions);
                    break;
            }
            driver.get(url);
            driver.manage().window().maximize();

        }
        @AfterTest
        public void tearDown(){
            // driver.quit();
        }

}
