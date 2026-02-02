package co.id.gradyfernando.pageObject;

import org.openqa.selenium.WebElement;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends AndroidActions {

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public void selectMenu(String menu) {
        WebElement menuElement = driver.findElement(parseElement("span", "title-text", menu));
        menuElement.click();    
    }
    
}
