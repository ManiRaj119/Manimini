package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobCategoriesPage extends PageBase {

    public JobCategoriesPage(WebDriver driver) {
        super(driver);
    }

    public void addCategory(String name) {
        clickAdd();
        typeLabeledInput("Name", name);
        clickSave();
        waitForToast();
    }

  
    public void deleteCategory(String name) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String deleteBtn = "//div[@role='row'][contains(., '" + name + "')]//button/i[contains(@class,'bi-trash')]/..";
        driver.findElement(By.xpath(deleteBtn)).click();

        By yesDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
        wait.until(ExpectedConditions.elementToBeClickable(yesDelete)).click();

        By dialog = By.cssSelector("div.oxd-dialog-container-default");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dialog));

        By row = By.xpath("//div[@role='row'][contains(., '" + name + "')]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(row));

        waitForToast();
    }

    public void deleteIfExists(String name) {
        if (isRowPresent(name)) {
            System.out.println("Deleting existing category: " + name);
            deleteCategory(name);   
        } else {
            System.out.println("Category not found, skipping delete: " + name);
        }
    }
}