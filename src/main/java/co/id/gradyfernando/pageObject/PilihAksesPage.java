package co.id.gradyfernando.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PilihAksesPage extends AndroidActions {

    @FindBy(xpath = "//h5[normalize-space()='Silahkan Pilih Hak Akses Anda']")
    private WebElement hakAksesTitle;

    public PilihAksesPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitUntilPageLoaded(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(hakAksesTitle));
    }

    public void waitPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Silahkan Pilih Hak Akses Anda\")"))
        );
    }

    public void clickLogout() {
        // driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Log Out\")")).click();
        By a = By.xpath("//button[@type='button' and normalize-space()='Log Out']");
        WebElement logoutButton = driver.findElement(a);
        logoutButton.click();
    }

    public void selectRole(String role) {
        WebElement selectRole = driver.findElement(parseElement("div", "role-info", role));
        selectRole.click();    
    }
    
}
