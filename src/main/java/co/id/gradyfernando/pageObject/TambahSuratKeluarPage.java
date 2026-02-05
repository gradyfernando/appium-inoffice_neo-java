package co.id.gradyfernando.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.beust.jcommander.internal.Nullable;

import co.id.gradyfernando.enums.TipeNomorMundur;
import co.id.gradyfernando.enums.TujuanPenerima;
import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TambahSuratKeluarPage extends AndroidActions {

    @FindBy(id = "field_letter_type_id")
    private WebElement jenisSuratElement;
    @FindBy(id = "field_letter_topic_id")
    private WebElement klasifikasiElement;

    @FindBy(id = "field_backdate_type")
    private WebElement tipeNomorMundurElement;
    @FindBy(id = "field_subject")
    private WebElement perihalElement;
    @FindBy(id = "field_is_secret")
    private WebElement isRahasiaElement;
    @FindBy(id = "field_content")
    private WebElement ringkasanElement;

    public TambahSuratKeluarPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setJenisSurat(String jenis) throws InterruptedException {
        WebElement webElement = jenisSuratElement.findElement(By.className("select2-selection__arrow"));
        webElement.click();

        Thread.sleep(1000);
        
        WebElement searchInput = driver.findElement(By.className("select2-search__field"));
        searchInput.sendKeys(jenis);
        
        Thread.sleep(1000);

        WebElement itemElement = driver.findElement(By.xpath("//li[@role='option' and contains(normalize-space(.), '"+jenis+"')]"));
        itemElement.click();
    }

    public void setKlasifikasi(String klasifikasi) throws InterruptedException {
        WebElement browseKlasifikasiElement = klasifikasiElement.findElement(By.xpath(".//button[@class='btn btn-orange btn-sm']"));
        browseKlasifikasiElement.click();
        Thread.sleep(1000);

        WebElement searchInput = driver.findElement(By.className("form-control"));
        searchInput.sendKeys(klasifikasi);
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement pilihButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//tr[.//td[normalize-space()='"+klasifikasi+"']]//button")
            )
        );
        pilihButton.click();

    }
    
    public void setPengirim(String pengirim) throws InterruptedException {
        WebElement container = driver.findElement(By.id("field_sender"));
        WebElement input = container.findElement(By.xpath(".//button[@class='btn btn-orange btn-sm']"));
        input.click();
        Thread.sleep(1000);

        WebElement searchInput = driver.findElement(By.className("form-control"));
        searchInput.sendKeys(pengirim);
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pilihButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//tr[td[contains(normalize-space(.),'"+ pengirim +"')]]//button")
            )
        );

        pilihButton.click();
    }

    public void setPenandatangan(String penandatangan) throws InterruptedException {
        WebElement container = driver.findElement(By.id("field_signers"));
        WebElement input = container.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm d-flex']"));
        input.click();
        Thread.sleep(1000);

        WebElement searchInput = driver.findElement(By.className("form-control"));
        searchInput.sendKeys(penandatangan);
        Thread.sleep(1500);

        driver.findElement(
            By.xpath("//tr[contains(., '"+ penandatangan +"')]//input[@type='checkbox']")
        ).click();

        driver.findElement(parseElement("button", "btn-primary", "Simpan")).click();
    }

    public void setTipeNomorMundur(TipeNomorMundur tipe, @Nullable String day) throws InterruptedException {
        scrollToId("field_backdate_type");
        WebElement selectNoMundurElement = tipeNomorMundurElement.findElement(By.className("select2-selection__arrow"));
        selectNoMundurElement.click();
        Thread.sleep(1000);

        WebElement itemElement = driver.findElement(By.xpath("//li[@role='option' and contains(normalize-space(.), '"+tipe+"')]"));
        itemElement.click();

        switch (tipe) {
            case Tanpa:
                break;
            case Otomatis:
            case Manual:
                if (day != null) {
                    WebElement tglElement = tipeNomorMundurElement.findElement(By.xpath(".//input[@type='text']"));
                    tglElement.click();

                    driver.findElement(
                        By.xpath("//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]//span[text()='" + day + "']")
                    ).click();
                } else {
                    throw new SkipException("Skip test - Tanggal tidak diisikan oleh QA");
                }
                break;
        }

    }

    public void setPenerima(TujuanPenerima kategori, String nama) throws InterruptedException {
        scrollToId("field_letter_recipients");

        if (kategori != TujuanPenerima.PenerimaJabatan &&
            kategori != TujuanPenerima.PenerimaPersonal &&
            kategori != TujuanPenerima.External &&
            kategori != TujuanPenerima.Template
        ) {
            throw new SkipException("Kategori yang ditulis tidak sesuai dengan field. Expected Penerima Jabatan / Penerima Personal / Template Penerima, but get " + kategori.getName());
        }

        // Tap tombol sesuai kategori
        WebElement input = driver.findElement(By.xpath("//div[@id='field_letter_recipients']//button[.//span[text()='" + kategori.getName() + "']]"));
        input.click();

        WebElement searchInput = driver.findElement(By.xpath("//input[@type='text' and @aria-label='Pencarian']"));
        searchInput.sendKeys(nama);

        Thread.sleep(2000);

        driver.findElement(
            By.xpath("//tr[contains(., '"+ nama +"')]//input[@type='checkbox']")
        ).click();

        driver.findElement(parseElement("button", "btn-primary", "Simpan")).click();
    }

    public void setPerihal(String perihal) {
        scrollToId("field_subject");

        WebElement perihalInput = perihalElement.findElement(By.className("form-control"));
        perihalInput.sendKeys(perihal);
    }

    public void setIsSuratRahasia(boolean isRahasia) {
        scrollToId("field_is_secret");

        WebElement isRahasiaCheckbox = isRahasiaElement.findElement(By.id("is_secret"));
        boolean isChecked = isRahasiaCheckbox.isSelected();
        
        if (isRahasia != isChecked) {
            isRahasiaCheckbox.click();
        }
    }

    public void setRingkasan(String ringkasan) {
        scrollToId("field_content");

        WebElement ringkasanInputText = ringkasanElement.findElement(By.id("content"));
        ringkasanInputText.sendKeys(ringkasan);
    }

    public void selectTembusan(TujuanPenerima kategori, String nama) throws InterruptedException {
        scrollToId("field_carbon_copy_recipients");

        if (kategori != TujuanPenerima.TembusanJabatan &&
            kategori != TujuanPenerima.TembusanPersonal &&
            kategori != TujuanPenerima.Template
        ) {
            throw new SkipException("Kategori yang ditulis tidak sesuai dengan field. Expected Tembusan Jabatan / Tembusan Personal / Template Penerima, but get " + kategori.getName());
        }

        // Tap tombol sesuai kategori
        WebElement input = driver.findElement(By.xpath("//div[@id='field_carbon_copy_recipients']//button[.//span[text()='" + kategori.getName() + "']]"));
        input.click();

        WebElement searchInput = driver.findElement(By.xpath("//input[@type='text' and @aria-label='Pencarian']"));
        searchInput.sendKeys(nama);

        Thread.sleep(2000);

        driver.findElement(
            By.xpath("//tr[contains(., '"+ nama +"')]//input[@type='checkbox']")
        ).click();

        driver.findElement(parseElement("button", "btn-primary", "Simpan")).click();
    }

    public void clickSimpan() {
        WebElement simpanBtn = driver.findElement(
            By.name("btn_submit_field_generator")
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            simpanBtn
        );

        simpanBtn.click();
    }

    public void commitTambahSuratKeluar() {
        WebElement confirmElement = driver.findElement(
            By.xpath("//div[contains(@class,'modal-footer')]//button[.//text()[normalize-space()='Tambah']]")
        );


        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            confirmElement
        );

        confirmElement.click();
    }

}
