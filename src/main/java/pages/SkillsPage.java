package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SkillsPage extends PageBase {

    public SkillsPage(WebDriver driver) {
        super(driver);
    }

    public void addSkill(String name, String description) {
        clickAdd();
        typeLabeledInput("Name", name);
        typeLabeledTextArea("Description", description);
        clickSave();
        waitForToast();
    }

    public void deleteSkill(String name) {
        
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        String dynamicDeleteXPath = "//div[@role='row'][contains(., '" + name + "')]//button/i[contains(@class, 'bi-trash')]/..";

        driver.findElement(By.xpath(dynamicDeleteXPath)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Yes, Delete')]"))).click();

        waitForRowToDisappear(name);
    }

    public void deleteIfExists(String name) {
        if (isRowPresent(name)) {
            deleteSkill(name);
        }
    }
}