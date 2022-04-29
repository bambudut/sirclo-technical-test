import org.openqa.selenium.By;

public class WebLocator {

    public final By headline = By.xpath("//h1");
    public final By errorText = By.xpath("//pre");
    public final By username = By.name("username");
    public final By password = By.name("password");
    public final By filterStart = By.name("start");
    public final By filterEnd = By.name("end");
    public final By button = By.xpath("//input[@value]");
    public final By tableData = By.xpath("//*//tbody//tr[2]");
}