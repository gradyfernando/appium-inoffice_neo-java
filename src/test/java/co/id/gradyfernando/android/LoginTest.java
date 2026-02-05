package co.id.gradyfernando.android;

import org.testng.annotations.Test;

import co.id.gradyfernando.pageObject.LoginPage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class LoginTest extends AndroidBaseTest {

	@Test
	public void test_login_and_change_company() {
		LoginPage page = new LoginPage(driver);
		page.setCompany("FrontendStaging");
		page.changeCompanyCode();
	}

	// @Test(dependsOnMethods = {"testGetContext"})
	// public void test_setCompanyCode_success() {
	// 	WebElement companyCodeElement = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"companyCode\"]"));
	// 	companyCodeElement.sendKeys("FrontendStaging");

	// 	WebElement lanjutkanButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"LANJUTKAN\"]"));
	// 	lanjutkanButton.click();
	// }
	
}
