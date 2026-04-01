package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidebarNav extends PageBase {

    public SidebarNav(WebDriver driver) {
        super(driver);
    }

    public void openRoot(String root) {
        By rootItem = By.xpath("//span[normalize-space()='" + root + "']/ancestor::a");
        clickable(rootItem).click();
        waitForBreadcrumb();
    }

    public void topbarClick(String item) {
        By topItem = By.xpath("//span[normalize-space()='" + item + "']");
        clickable(topItem).click();
        waitForBreadcrumb();
    }


    public void openJobCategories() {
        openRoot("Admin");
        topbarClick("Job");
        
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]")).click();
    }
    public void openLocations() {
        openRoot("Admin");
        topbarClick("Organization");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]/ul/li[2]")).click();
    }
    

    public void openSkills() {
        openRoot("Admin");
        topbarClick("Qualifications");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[1]")).click();
    }
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        
        By userDropdown = By.className("oxd-userdropdown-tab");
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        
        By logoutLink = By.xpath("//a[text()='Logout']");
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        
        System.out.println(" Logged out successfully");
    }
}