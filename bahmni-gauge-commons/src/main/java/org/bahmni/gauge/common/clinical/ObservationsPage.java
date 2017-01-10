package org.bahmni.gauge.common.clinical;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.clinical.domain.ObservationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.io.IOException;
import java.util.List;

//import javafx.scene.control.Tab;

public class ObservationsPage extends BahmniPage {

    @FindBy(how = How.CSS, using = "#template-control-panel-button")
    public WebElement addFormbutton;

    @FindBy(how = How.CSS, using = "button[id*=\"chief_complaint_data_addmore_observation\"]")
    public WebElement addmore;

    @FindBy(how = How.CSS, using = "#dashboard-link")
    public WebElement dashboard;

    @FindBy(how = How.CSS, using = ".leaf-observation-node")  //.dashboard-content
    public List<WebElement> observation_nodes;

    @FindBy(how = How.CSS, using = ".template-control-panel")
    public WebElement templatePanel;

    @FindBy(how = How.CSS, using = ".concept-set-panel-wrap")
    public WebElement templatePanelLeft;

    @FindBy(how = How.CSS, using = ".concept-set-title")
    public List<WebElement> observationTemplates;

    @FindBy(how = How.CSS, using = ".save-consultation")
    public WebElement save;

    @FindBy(how = How.XPATH, using = "//span[text()='Adverse Effects']/../../div//input")
    public WebElement adverseEffects;


    public WebElement selectTemplate(String templateName) {
        clickTemplateButton();
        List<WebElement> allForms = templatePanelLeft.findElements(By.tagName("span"));

        for (int i = 0; i < allForms.size(); i++) {
            String text = allForms.get(i).getText().replace(" ", "_"); //For debugging
            if (text.contains(templateName)) {
                allForms.get(i).click();
                break;
            }
        }

        WebElement downKey = waitForElementwithTimeOut(driver, ExpectedConditions.presenceOfElementLocated(By.cssSelector("#" + templateName + " h2 i.icon-bahmni-expand:not(.ng-hide)")), 10);
        WebElement template = driver.findElement(getSectionWithChildHavingId(templateName));
        if (downKey != null) { //checking if down key exists
            return template;
        }
        return null;
    }

    @Deprecated
    public WebElement expandObservationTemplate(String templateId) {
        WebElement downKey = waitForElementwithTimeOut(driver, ExpectedConditions.presenceOfElementLocated(By.cssSelector("#" + templateId + " h2 i.icon-bahmni-expand:not(.ng-hide)")), 10);
        WebElement template = driver.findElement(getSectionWithChildHavingId(templateId));
        if (downKey != null) { //checking if down key exists
            return template;
        }

        WebElement expandArrow = driver.findElement(By.cssSelector("#" + templateId + " h2 i.icon-bahmni-collapse:not(.ng-hide)"));
        if (null != expandArrow) {
            expandArrow.click();
        }
        waitForSpinner();
        return template;
    }

    public void clickTemplateButton() {
        addFormbutton.click();
    }

    @Deprecated
    public void fillTemplateData(Table table, ObservationForm form) {

        WebElement observ_label = null;
        WebElement answer;
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();

        for (int i = 0; i <= observation_nodes.size() - 1 && i <= columnNames.size() - 1; i++) {
            observ_label = observation_nodes.get(i).findElement(By.tagName("label"));
            if (observ_label.getText().contains(columnNames.get(i))) {
                answer = observation_nodes.get(i).findElement(By.cssSelector(".field-value"));
                if (answer.getText().length() != 0) {
                    if (answer.findElement(By.tagName("button")).isEnabled()) {
                        Point point = answer.findElement(By.tagName("button")).getLocation();
                        answer.findElement(By.tagName("button")).getLocation().move(point.getX(), point.getY());
                        answer.findElement(By.tagName("button")).click();
                    }
                } else {
                    if (answer.findElement(By.tagName("input")).isEnabled()) {
                        Point point = answer.findElement(By.tagName("input")).getLocation();
                        answer.findElement(By.tagName("input")).getLocation().move(point.getX(), point.getY());
                        answer.findElement(By.tagName("input")).sendKeys(rows.get(0).getCell(columnNames.get(i)));
                    }
                }
            }
        }
    }

    private By getSectionWithChildHavingId(String templateId) {
        return By.xpath("//section[contains(@class,\" section-grid\") and descendant::*[@id=\"" + templateId + "\"]]");
    }

    //*[@id="Patient_Vitals"]/h2/span

    public void enterObservations(String template, Table data) {
        ObservationForm observationForm = new ObservationForm(expandObservationTemplate(template.replace(' ', '_')));
        observationForm.fillUp(data);
        save.click();
        storeObservationFormInSpecStore(observationForm);
    }

    public void navigateToDashboard() {
        dashboard.click();
    }

    public void selectSuggestion(String enteredValue) {
        List<WebElement> elements = this.driver.findElements(By.cssSelector(".suggestion-item"));
        for (WebElement element : elements) {
//            if (element.getText().equals(enteredValue))
            element.click();
            break;
        }
    }

    public void addMoreObservation() {
        addmore.click();

    }

    int rowCount;

    public void addChiefComplaints(String template, Table data) {
        ObservationForm observationForm = new ObservationForm(expandObservationTemplate(template.replace(' ', '_')));
        List<TableRow> rows = data.getTableRows();
        List<String> columnNames = data.getColumnNames();
        rowCount = 1;
        String value;
        for (TableRow row : rows) {
            value = row.getCell(columnNames.get(0));
            driver.findElement(By.xpath(("(.//*[contains(@id,'observation_')])[" + rowCount + "]"))).sendKeys(value);
            driver.findElement(By.xpath("(.//*[contains(@id,'observation_')])[" + rowCount + "]/../div/button")).click();
            rowCount++;
            addmore.click();

        }
        save.click();
    }


    public void removeChiefComplaints(String template, Table data) {
       ObservationForm observationForm = new ObservationForm(expandObservationTemplate(template.replace(' ', '_')));
        List<TableRow> rows = data.getTableRows();
        int rowSize=rows.size();
        List<String> columnNames = data.getColumnNames();
        String value;
        int rowCount=1;
        for (TableRow row : rows) {
            value = row.getCell(columnNames.get(0));

            for (rowCount = 1; rowCount <= rowSize; rowCount++) {
                WebElement element = driver.findElement(By.xpath(("(.//*[contains(@id,'observation_')])[" + rowCount + "]")));
                if (value.equals(element.getAttribute("value")) && (rowCount!=1)) {
                    System.out.println("Removed the Element ---------------->"+element.getAttribute("value"));
                    driver.findElement(By.xpath(("(.//*[contains(@id,'removeClonedObs')])[" + rowCount + "]"))).click();

                }


            }
        }
        save.click();
    }

}
