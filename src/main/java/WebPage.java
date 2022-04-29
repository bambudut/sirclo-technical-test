import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebPage {

    WebDriver webDriver;
    WebDriverWait driverWait;
    WebModel webModel = new WebModel();
    WebLocator webLocator = new WebLocator();
    String baseUrl = "https://qa-interview.srcli.xyz";

    public WebPage(WebDriver driver) {
        this.webDriver = driver;
        driverWait = new WebDriverWait(driver, 15);
    }

    public void verifyMainPage() {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (webDriver.findElement(webLocator.headline).isDisplayed()) {
            String textPage = webDriver.findElement(webLocator.headline).getText();
            System.out.println("Redirected to main page, text: " + textPage);
        } else {
            System.out.println("Failed to be redirect, text not displayed");
        }
    }

    public void verifyLogin(String password, boolean isSuccess) {
        webDriver.get(baseUrl + "/login");
        webDriver.findElement(webLocator.username).sendKeys(webModel.username);
        webDriver.findElement(webLocator.password).sendKeys(password);
        webDriver.findElement(webLocator.button).click();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (isSuccess) {
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            System.out.println("Login is success, will redirect to main page");
        } else {
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            String getError = webDriver.findElement(webLocator.errorText).getText();
            System.out.println("Error message: " + getError);
        }
    }

    public void verifyDataPage() {
        webDriver.get(baseUrl + "/data");
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        if (webDriver.findElements(webLocator.tableData).size() > 1) {
            System.out.println("User already login, table data is displayed");
        } else if (webDriver.findElement(webLocator.username).isDisplayed()) {
            System.out.println("User not yet login, please login to continue");
        } else {
            verifyMainPage();
        }
    }

    public void setFilterData(String start, String end) {
        webDriver.findElement(webLocator.filterStart).sendKeys(start);
        webDriver.findElement(webLocator.filterEnd).sendKeys(end);
        webDriver.findElement(webLocator.button).click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (webDriver.findElements(webLocator.tableData).size() > 1) {
            System.out.println("Input filter correct, table data is displayed");
        } else if (webDriver.findElements(webLocator.tableData).size() == 0) {
            System.out.println("Date format wrong, invalid range data or no data in range");
        } else {
            verifyMainPage();
        }
    }

    public void verifyLogout(boolean isCookieDeleted) {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.get(baseUrl + "/logout");
        if (isCookieDeleted) {
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            webDriver.get(baseUrl);
            verifyMainPage();
        } else {
            verifyDataPage();
        }
    }

    public void verifyInvalidEndpoint(String endpoint) {
        webDriver.get(baseUrl + "/" + endpoint);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (webDriver.findElement(webLocator.errorText).isDisplayed()) {
            String getError = webDriver.findElement(webLocator.errorText).getText();
            System.out.println("Error message: " + getError);
        } else {
            System.out.println("Failed to verify invalid endpoint route");
        }
    }
}