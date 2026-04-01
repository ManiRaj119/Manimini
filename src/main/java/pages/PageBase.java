package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public abstract class PageBase {

    protected WebDriver driver;


    private final By breadcrumb = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]");
    protected PageBase(WebDriver driver) {
        this.driver = driver;
    }
    protected WebDriverWait wdWait(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    protected WebElement visible(By locator) {
        return wdWait(12).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected WebElement clickable(By locator) {
        return wdWait(12).until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected boolean invisible(By locator) {
        return wdWait(12).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void waitForBreadcrumb() {
        visible(breadcrumb);
    }
    protected void waitForToast() {
        By toast = By.cssSelector(".oxd-toast-content");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(toast));

            wait.until(ExpectedConditions.invisibilityOfElementLocated(toast));

        } catch (Exception ignored) {
           
        }
    }

    protected void typeLabeledInput(String label, String value) {
        By input = By.xpath("//label[normalize-space()='" + label + "']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]//input");
        WebElement el = visible(input);
        el.clear();
        el.sendKeys(value);
    }
    protected void typeLabeledTextArea(String label, String value) {
        By ta = By.xpath("//label[normalize-space()='" + label + "']" +
                         "/ancestor::div[contains(@class,'oxd-input-group')]//textarea");
        WebElement el = visible(ta);
        el.clear();
        el.sendKeys(value);
    }
    protected void selectDropdownByLabel(String label, String visibleText) {
        By container = By.xpath("//label[normalize-space()='" + label + "']" +
                "/ancestor::div[contains(@class,'oxd-input-group')]" +
                "//div[contains(@class,'oxd-select-text--after')]");
        clickable(container).click();

        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + visibleText + "']");
        clickable(option).click();
    }

    protected By rowByExactName(String name) {
        return By.xpath("//div[contains(@class,'oxd-table-body')]" +
                        "//div[contains(@class,'oxd-table-row')]" +
                        "[.//div[normalize-space()='" + name + "']]");
    }
    public void waitForRowToBePresent(String name) {
        wdWait(12).until(ExpectedConditions.visibilityOfElementLocated(rowByExactName(name)));
    }
    public void waitForRowToDisappear(String name) {
        wdWait(12).until(ExpectedConditions.invisibilityOfElementLocated(rowByExactName(name)));
    }
    public boolean isRowPresent(String name) {
        try {
            driver.findElement(rowByExactName(name));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void clickAdd()  {
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        By addButton = By.xpath("//button[.//i[contains(@class,'plus')]] | //button[normalize-space()='Add']");
        clickable(addButton).click();
    }
    public void clickSave() {
        clickable(By.xpath("//button[normalize-space()='Save']")).click();
    }
    public void deleteByName(String name) {
        By deleteBtn = By.xpath(
            "//div[contains(@class,'oxd-table-body')]//div[contains(@class,'oxd-table-row')]" +
            "[.//div[normalize-space()='" + name + "']]//button[.//i[contains(@class,'trash')]]"
        );
        clickable(deleteBtn).click();
        clickable(By.xpath("//button[normalize-space()='Yes, Delete']")).click();
        waitForToast();
    }
}