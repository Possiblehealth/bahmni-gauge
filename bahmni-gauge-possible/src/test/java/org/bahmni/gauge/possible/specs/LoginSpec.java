package org.bahmni.gauge.possible.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.login.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSpec {

    private final WebDriver driver;

    public LoginSpec(){
        this.driver = DriverFactory.getDriver();
    }

    @BeforeClassSteps
    public void waitForAppReady(){
        BahmniPage.waitForSpinner(driver);
    }

    @Step("Login as default user on <location>")
    public void login(String location){
        LoginPage loginPage = PageFactory.get(LoginPage.class);
        loginPage.waitForElementOnPage("#username");
        loginPage.login(System.getenv("BAHMNI_GAUGE_APP_USER"), System.getenv("BAHMNI_GAUGE_APP_PASSWORD"), location);
    }
}
