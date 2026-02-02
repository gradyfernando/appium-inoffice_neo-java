package co.id.gradyfernando.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class TambahSuratMasukPage extends AndroidActions {

    public TambahSuratMasukPage(AndroidDriver driver) {
        super(driver);
    }

    public void setNomorSurat(String nomorSurat) {
        WebElement noSuratElement = driver.findElement(By.name("letter_number"));
        noSuratElement.sendKeys(nomorSurat);
    }

    public void setTglSurat(String tglSurat) {
        By dateInput = By.xpath("//input[@type='text' and @placeholder='Pilih tanggal...']");
        WebElement tglSuratElement = driver.findElement(dateInput);
        tglSuratElement.click();
    }

    
}
