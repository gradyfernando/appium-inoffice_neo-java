package co.id.gradyfernando.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends AndroidActions {
    
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void changeCompanyCode() {
		WebElement changeCompanyCodeButton = driver.findElement(
			By.cssSelector("button.btn-link")
		);
        changeCompanyCodeButton.click();
    }

    public void setCompany(String companyCode) {
		WebElement companyCodeElement = driver.findElement(By.id("companyCode"));
		companyCodeElement.sendKeys(companyCode);

		WebElement lanjutkanBtn = driver.findElement(
			By.cssSelector("button.btn-sign")
		);
		lanjutkanBtn.click();
    }

    public void setUserCredential(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id("loginusername"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("loginpassword"));
        passwordInput.sendKeys(password);

		WebElement masukButton = driver.findElement(
			By.cssSelector("button.btn-sign")
		);
        masukButton.click();
    }
    
}
