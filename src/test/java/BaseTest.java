import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class BaseTest {

    String driverPath;
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @BeforeMethod
    public void initDriver() {
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/driver.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String os = properties.getProperty("os");

            switch (os) {
                case "macos":
                    driverPath = "/driver/chrome/chromedriver_mac";
                    break;
                case "linux":
                    driverPath = "/driver/chrome/chromedriver_nux";
                    break;
                case "windows":
                    driverPath = "/driver/chrome/chromedriver_win.exe";
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        webDriver = new ChromeDriver(options);
        webDriver.get("https://qa-interview.srcli.xyz");
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, 15);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}