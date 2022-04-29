import org.testng.annotations.Test;

public class WebTest extends BaseTest {

    WebPage webPage;
    WebModel webModel = new WebModel();

    @Test(priority = 1)
    public void verifyOpenMainPage() {
        webPage = new WebPage(webDriver);
        webPage.verifyMainPage();
    }

    @Test(priority = 2)
    public void verifyLoginWithValidCredentials() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidCredentials() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.invalidPassword, false);
    }

    @Test(priority = 4)
    public void verifyGoToInvalidEndpoint() {
        webPage = new WebPage(webDriver);
        webPage.verifyInvalidEndpoint("profile");
    }

    @Test(priority = 5)
    public void verifyGoToDataPageWithLogin() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
        webPage.verifyDataPage();
    }

    @Test(priority = 6)
    public void verifyGoToDataPageWithoutLogin() {
        webPage = new WebPage(webDriver);
        webPage.verifyDataPage();
    }

    @Test(priority = 7)
    public void verifyLogoutWithDeleteCookie() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
        webPage.verifyLogout(true);
        webPage.verifyDataPage();
    }

    @Test(priority = 8)
    public void verifyLogoutWithoutDeleteCookie() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
        webPage.verifyLogout(false);
        webPage.verifyDataPage();
    }

    @Test(priority = 9)
    public void verifySetFilterWithValidReturnData() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
        webPage.verifyDataPage();
        webPage.setFilterData(webModel.validStart, webModel.validEnd);
    }

    @Test(priority = 10)
    public void verifySetFilterWithNotFoundData() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
        webPage.verifyDataPage();
        webPage.setFilterData(webModel.noDataStart, webModel.noDataEnd);
    }

    @Test(priority = 11)
    public void verifySetFilterWithInvalidFormat() {
        webPage = new WebPage(webDriver);
        webPage.verifyLogin(webModel.validPassword, true);
        webPage.verifyDataPage();
        webPage.setFilterData(webModel.invalidStartFormat, webModel.invalidEndFormat);
    }
}