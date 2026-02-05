package co.id.gradyfernando.android;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.id.gradyfernando.enums.TipeNomorMundur;
import co.id.gradyfernando.enums.TujuanPenerima;
import co.id.gradyfernando.pageObject.DaftarSuratPage;
import co.id.gradyfernando.pageObject.HomePage;
import co.id.gradyfernando.pageObject.LoginPage;
import co.id.gradyfernando.pageObject.PilihAksesPage;
import co.id.gradyfernando.pageObject.TambahSuratKeluarPage;
import co.id.gradyfernando.pageObject.TambahSuratMasukPage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class TransactionTest extends AndroidBaseTest {

    private HomePage homePage;

    @BeforeClass
    public void login() throws InterruptedException {
        LoginPage page = new LoginPage(driver);
		page.setCompany("FrontendStaging");
		page.setUserCredential("4dminIntegra", "admin123");
		Thread.sleep(1000);

		PilihAksesPage aksesPage = new PilihAksesPage(driver);
		aksesPage.selectRole("Admin System");
		Thread.sleep(1000);

		homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void gotoHomePage() throws InterruptedException {
        homePage.showPage();
        Thread.sleep(1000);
    }

	@Test
	public void test_addSuratMasuk_fillAllField() throws InterruptedException {
		homePage.selectMenu("Surat Masuk");
		Thread.sleep(1000);

		DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
		daftarSuratPage.addSuratMasuk("Penting");
		Thread.sleep(2000);

		TambahSuratMasukPage tambahSuratMasukPage = new TambahSuratMasukPage(driver);

        String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
		tambahSuratMasukPage.setNomorSurat("ITS/AT-TEST/" + timeStamp.toString());
		tambahSuratMasukPage.selectFlatpickrDate("field_letter_date", "1");
		tambahSuratMasukPage.selectFlatpickrDate("field_letter_received_at", "1");
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

		Thread.sleep(4000);
	}  

    @Test
    public void test_addSuratKeluar_fillAllField() throws InterruptedException {
		homePage.selectMenu("Surat Keluar");
        Thread.sleep(1000);

		DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
		daftarSuratPage.addSuratKeluar();

        Thread.sleep(2000);
        TambahSuratKeluarPage tambahSuratKeluarPage = new TambahSuratKeluarPage(driver);
        tambahSuratKeluarPage.setJenisSurat("INSTRUKSI");
        tambahSuratKeluarPage.setKlasifikasi("IN.01.00");
        tambahSuratKeluarPage.setPengirim("JAROT");
        tambahSuratKeluarPage.setPenandatangan("HARRIS");
		tambahSuratKeluarPage.setTipeNomorMundur(TipeNomorMundur.Tanpa, null);
		tambahSuratKeluarPage.setPenerima(TujuanPenerima.PenerimaJabatan, "RUSMANTO");
		tambahSuratKeluarPage.setPerihal("Testing Automaton");
		tambahSuratKeluarPage.setIsSuratRahasia(true);
		tambahSuratKeluarPage.setRingkasan("Testing Automaton");
		tambahSuratKeluarPage.selectTembusan(TujuanPenerima.TembusanJabatan, "ABDA");
		tambahSuratKeluarPage.clickSimpan();
		tambahSuratKeluarPage.commitTambahSuratKeluar();

        Thread.sleep(2000);
    }
    
}
