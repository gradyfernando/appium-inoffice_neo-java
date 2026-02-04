package co.id.gradyfernando.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

    public void selectFlatpickrDate(String fieldId, String day) {
        WebElement container = driver.findElement(By.id(fieldId));
        WebElement input = container.findElement(By.xpath(".//input[@type='text']"));
        input.click();

        driver.findElement(
            By.xpath("//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]//span[text()='" + day + "']")
        ).click();
    }

    public void selectPengirimByTypeAndDropDown(String pengirim) throws InterruptedException {
        WebElement pengirimElement = driver.findElement(By.name("external"));
        pengirimElement.sendKeys(pengirim);

        Thread.sleep(4000);

        driver.findElement(
            By.xpath("//span[text()='"+ pengirim +"']/ancestor::button")
        ).click();
    }

    public void setAlamat(String alamat) {
        WebElement alamatElement = driver.findElement(By.name("sender_external_address"));
        alamatElement.sendKeys(alamat);
        driver.hideKeyboard();
    }

    public void setPerihal(String perihal) {
        WebElement alamatElement = driver.findElement(By.name("subject"));
        alamatElement.sendKeys(perihal);
        driver.hideKeyboard();
    }

    public void setContent(String content) {
        WebElement alamatElement = driver.findElement(By.name("content"));
        alamatElement.sendKeys(content);
        driver.hideKeyboard();
    }

    public void selectPenerima(String label) {
        WebElement input = driver.findElement(By.xpath("//div[@id='field_letter_recipients']//button[.//span[text()='" + label + "']]"));
        input.click();

        driver.findElement(
            By.xpath("//tr[.//label[normalize-space()='"+label+"']]//label[normalize-space()='"+label+"']")
        ).click();
    }

    public void searchUserPenerima(String kategori, String keyword) throws InterruptedException {
        WebElement input = driver.findElement(By.xpath("//div[@id='field_letter_recipients']//button[.//span[text()='" + kategori + "']]"));
        input.click();

        WebElement searchInput = driver.findElement(By.xpath("//input[@type='text' and @aria-label='Pencarian']"));
        searchInput.sendKeys(keyword);

        Thread.sleep(2000);

        driver.findElement(
            By.xpath("//tr[contains(., '"+ keyword +"')]//input[@type='checkbox']")
        ).click();

        driver.findElement(parseElement("button", "btn-primary", "Simpan")).click();
    }


    public void selectTembusan(String label) {
        WebElement input = driver.findElement(By.xpath("//div[@id='field_carbon_copy_recipients']//button[.//span[text()='" + label + "']]"));
        input.click();

        driver.findElement(
            By.xpath("//tr[.//label[normalize-space()='"+label+"']]//label[normalize-space()='"+label+"']")
        ).click();
    }

    public void searchUserTembusan(String kategori, String keyword) throws InterruptedException {
        WebElement input = driver.findElement(By.xpath("//div[@id='field_carbon_copy_recipients']//button[.//span[text()='" + kategori + "']]"));
        input.click();

        WebElement searchInput = driver.findElement(By.xpath("//input[@type='text' and @aria-label='Pencarian']"));
        searchInput.sendKeys(keyword);

        Thread.sleep(2000);

        driver.findElement(
            By.xpath("//tr[contains(., '"+ keyword +"')]//input[@type='checkbox']")
        ).click();

        driver.findElement(parseElement("button", "btn-primary", "Simpan")).click();
    }

    public void setLampiranTidakDisertakan(String alasan) throws InterruptedException {
        scrollToAttachments();
        Thread.sleep(1000);
        // scrollUntilTextFound("Lampiran sengaja tidak disertakan");

        WebElement element = driver.findElement(By.id("is_without_attachment_0"));
        element.click();

        WebElement noteElement = driver.findElement(By.id("without_attachment_note"));
        noteElement.sendKeys(alasan);
        driver.hideKeyboard();
    }

    public WebElement scrollToAttachments() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 20; i++) {
            List<WebElement> els = driver.findElements(By.id("field_attachments"));
            if (!els.isEmpty()) {
                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    els.get(0)
                );
                return els.get(0);
            }

            js.executeScript(
                "document.documentElement.scrollBy(0, window.innerHeight * 0.8);"
            );

            try { Thread.sleep(300); } catch (Exception e) {}
        }

        throw new NoSuchElementException("field_attachments not found");
    }

    public void setKeterangan(String keterangan) {
        WebElement element = driver.findElement(By.name("keterangan"));
        element.sendKeys(keterangan);
        driver.hideKeyboard();
    }

    public void submitTambahSuratMasuk() {
        WebElement simpanBtn = driver.findElement(
            By.name("btn_submit_field_generator")
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            simpanBtn
        );

        simpanBtn.click();
    }

    public void commitTambahSuratMasuk() {
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
