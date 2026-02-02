package co.id.gradyfernando.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class DaftarSuratPage extends AndroidActions {

    public DaftarSuratPage(AndroidDriver driver) {
        super(driver);
    }

    public void addSurat(String sifat) {
        WebElement addButton = driver.findElement(By.id("dropdownMenuButton1"));
        addButton.click();

        WebElement sifatButton = driver.findElement(parseElement("a", "dropdown-item", sifat));
        sifatButton.click();
    }    
    
}
