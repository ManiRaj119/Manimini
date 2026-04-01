package pages;

import org.openqa.selenium.WebDriver;

public class LocationsPage extends PageBase {

    public LocationsPage(WebDriver driver) {
        super(driver);
    }

    public void addLocation(String name, String city, String country) {
        clickAdd();
        typeLabeledInput("Name", name);
        typeLabeledInput("City", city);
        selectDropdownByLabel("Country", country);
        clickSave();
        waitForToast();
    }

    public void deleteLocation(String name) {
        deleteByName(name);
        waitForRowToDisappear(name);
    }

    public void deleteIfExists(String name) {
        if (isRowPresent(name)) {
            deleteLocation(name);
        }
    }
}