package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import pages.SidebarNav;
import pages.LoginPage;
import pages.JobCategoriesPage;
import pages.LocationsPage;
import pages.SkillsPage;

public class OrangeHRMCaseStudyTest extends BaseTest {

    @Test
    public void caseStudy_deleteSkillsSet_simple() {

        String url = ConfigReader.get("base.url");
        String user = ConfigReader.get("username");
        String pass = ConfigReader.get("password");

        String jobCategory    = "Automation Engineer";
        String locationName   = "Chennai Tech Park";
        String locationCity   = "Chennai";
        String locationCountry= "India";
        String skillName      = "Automation Demo";
        String skillDesc      = "Temporary skill for automation case study";

        LoginPage login       = new LoginPage(driver);
        SidebarNav nav        = new SidebarNav(driver);
        JobCategoriesPage job = new JobCategoriesPage(driver);
        LocationsPage loc     = new LocationsPage(driver);
        SkillsPage skills     = new SkillsPage(driver);

        login.open(url);
        login.login(user, pass);
        System.out.println("Logged into OrangeHRM");

        nav.openJobCategories();
        job.deleteIfExists(jobCategory);
        job.addCategory(jobCategory);
        job.waitForRowToBePresent(jobCategory);
        Assert.assertTrue(job.isRowPresent(jobCategory), "Job Category was not added.");

        job.deleteCategory(jobCategory);
        Assert.assertFalse(job.isRowPresent(jobCategory), "Job Category was not deleted.");
        System.out.println(" Job Category flow passed");

        nav.openLocations();
        loc.deleteIfExists(locationName);
        loc.addLocation(locationName, locationCity, locationCountry);
        loc.waitForRowToBePresent(locationName);
        Assert.assertTrue(loc.isRowPresent(locationName), "Location was not added.");

        loc.deleteLocation(locationName);
        Assert.assertFalse(loc.isRowPresent(locationName), "Location was not deleted.");
        System.out.println(" Location flow passed");

        nav.openSkills();
        skills.deleteIfExists(skillName);
        skills.addSkill(skillName, skillDesc);
        skills.waitForRowToBePresent(skillName);
        Assert.assertTrue(skills.isRowPresent(skillName), "Skill was not added.");

        skills.deleteSkill(skillName);
        Assert.assertFalse(skills.isRowPresent(skillName), "Skill was not deleted.");
        System.out.println(" Skill flow passed");
        nav.logout();
    }
}