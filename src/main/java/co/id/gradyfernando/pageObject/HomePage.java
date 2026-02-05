package co.id.gradyfernando.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import co.id.gradyfernando.config.Route;
import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends AndroidActions {

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public void showPage() {
        // WebElement dashboardLink = driver.findElement(By.id("ember132"));
        // dashboardLink.click();
        driver.executeScript("window.location.href='"+Route.Dashboard.getName()+"'");
    }

    public void selectMenu(String menu) {
        WebElement menuElement = driver.findElement(parseElement("span", "title-text", menu));
        menuElement.click();    
    }
    
}
