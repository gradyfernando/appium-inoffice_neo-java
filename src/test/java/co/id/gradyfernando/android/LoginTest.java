package co.id.gradyfernando.android;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import co.id.gradyfernando.pageObject.DaftarSuratPage;
import co.id.gradyfernando.pageObject.HomePage;
import co.id.gradyfernando.pageObject.LoginPage;
import co.id.gradyfernando.pageObject.PilihAksesPage;
import co.id.gradyfernando.pageObject.TambahSuratMasukPage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class LoginTest extends AndroidBaseTest {

	@Test
	public void test_login_and_change_company() {
		LoginPage page = new LoginPage(driver);
		page.setCompany("FrontendStaging");
		page.changeCompanyCode();
	}

	@Test
	public void test_addSuratMasuk_fillAllField() throws InterruptedException {
		LoginPage page = new LoginPage(driver);
		page.setCompany("FrontendStaging");
		page.setUserCredential("4dminIntegra", "admin123");
		Thread.sleep(1000);

		PilihAksesPage aksesPage = new PilihAksesPage(driver);
		aksesPage.selectRole("Admin System");
		Thread.sleep(1000);

		HomePage homePage = new HomePage(driver);
		homePage.selectMenu("Surat Masuk");
		Thread.sleep(1000);

		DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
		daftarSuratPage.addSurat("Penting");
		Thread.sleep(2000);

		TambahSuratMasukPage tambahSuratMasukPage = new TambahSuratMasukPage(driver);
		LocalDate today = LocalDate.now();
		tambahSuratMasukPage.setNomorSurat("ITS/AT-TEST/" + today.toString());
		tambahSuratMasukPage.selectFlatpickrDate("field_letter_date", "1");
		tambahSuratMasukPage.selectFlatpickrDate("field_letter_received_at", "2");
		tambahSuratMasukPage.selectPengirimByTypeAndDropDown("Bank BI");
		tambahSuratMasukPage.setPerihal("Selamat datang di inOffice");
		tambahSuratMasukPage.setContent("Selamat datang di inOffice");
		tambahSuratMasukPage.searchUserPenerima("Jabatan", "FIRDAUS");
		tambahSuratMasukPage.searchUserPenerima("Personal", "ABDA");
		tambahSuratMasukPage.searchUserTembusan("Jabatan", "RUSMANTO");
		tambahSuratMasukPage.setLampiranTidakDisertakan("Tidak memiliki lampiran");
		tambahSuratMasukPage.setKeterangan("Created by: Automaton Test");
		tambahSuratMasukPage.submitTambahSuratMasuk();

		Thread.sleep(2000);
		tambahSuratMasukPage.commitTambahSuratMasuk();

		Thread.sleep(1000);
	}

	// @Test(dependsOnMethods = {"testGetContext"})
	// public void test_setCompanyCode_success() {
	// 	WebElement companyCodeElement = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"companyCode\"]"));
	// 	companyCodeElement.sendKeys("FrontendStaging");

	// 	WebElement lanjutkanButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"LANJUTKAN\"]"));
	// 	lanjutkanButton.click();
	// }
	
}
