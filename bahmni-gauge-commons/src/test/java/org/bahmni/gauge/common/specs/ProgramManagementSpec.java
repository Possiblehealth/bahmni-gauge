package org.bahmni.gauge.common.specs;

import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.TestSpecException;
import org.bahmni.gauge.common.program.ProgramManagementPage;
import org.bahmni.gauge.common.program.domain.PatientProgram;
import org.bahmni.gauge.common.program.domain.Program;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.bahmni.gauge.util.TableTransformer;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProgramManagementSpec {

	protected ProgramManagementPage programManagementPage = null;

	@BeforeClassSteps
	public void waitForAppReady(){
		BahmniPage.waitForSpinner(DriverFactory.getDriver());
	}

	public ProgramManagementSpec(){
		this.programManagementPage = PageFactory.getProgramManagementPage();
	}

	@Step("Register the patient to following program <programDetails>")
	public void enrollPatientToProgram(Table programDetails) throws Exception {
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		Program treatment = programManagementPage.transformTableToProgram(programDetails);
		programManagementPage.storeProgramInSpecStore(treatment);
		programManagementPage.enrollPatientToProgram(treatment);
		waitForAppReady();
	}

	@Step("Ensure that the patient is registered to mentioned program")
	public void verifyThePatientIsEnrolledToTheProgram() {
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		Program programDetails = programManagementPage.getProgramFromSpecStore();
		Assert.assertTrue(programManagementPage.isPatientEnrolledToProgram(programDetails));
	}

	@Step("Ensure that the patient is registered to <program>")
	public void verifyThePatientIsEnrolledToGivenProgram(String programName) {
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		WebElement program = programManagementPage.findProgram(programName);
		Assert.assertNotNull("Patient is not enrolled to program "+programName+". Expected to be enrolled", program);
	}

	@Step("Edit attribute to org.bahmi.gauge.possible.registration <org.bahmi.gauge.possible.registration> and facility <facility>")
	public void editAttributesEnrolledToTheProgram(String registration, String facility) {
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		Program programDetails = programManagementPage.getProgramFromSpecStore();
		programManagementPage.editProgramAttributes(programDetails, registration, facility);
		waitForAppReady();
	}

	@Step("End the program <table>")
	public void endTheProgram(Table program) throws Exception {
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		programManagementPage.endProgram(transformTableToProgram(program));
	}

	@Step("End previously mentioned program")
	public void endPreviousProgram(){
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		Program program=new BahmniPage().getProgramFromSpecStore();
		program.setTreatmentStatus("Treatment Outcome, Cured");
		programManagementPage.endProgram(program);
	}

	@Step("End previously mentioned program by setting outcome to <outcome>")
	public void endPreviousProgramWithOutcome(String outcome){
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		Program program=new BahmniPage().getProgramFromSpecStore();
		program.setTreatmentStatus(outcome);
		programManagementPage.endProgram(program);
	}

	@Step("Enroll patient to the program <table>")
	public void enrollPatientToTheProgram(Table table) throws Exception {
		Program program = TableTransformer.asEntity(table,Program.class);
		Patient patient = PageFactory.getRegistrationFirstPage().getPatientFromSpecStore();

		PatientProgram patientProgram = new PatientProgram();
		patientProgram.setPatient(patient);
		patientProgram.setProgram(program);
		BahmniRestClient.get().enrollToProgram(patientProgram);
		programManagementPage.storeProgramInSpecStore(program);
		programManagementPage.storePatientProgramInSpecStore(patientProgram);
	}

	@Step("Click on the patients previously program enrolled")
	public void clickOnThePatientsPreviouslyProgramEnrolled(){
		programManagementPage.clickTreatmentDashboard(programManagementPage.getPatientProgramFromSpecStore());
		waitForAppReady();
	}

	@Step("Unenroll patient from <programName> program")
	public void unenrollPatientFromTheProgram(String programName){
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		WebElement program = programManagementPage.findProgram(programName);
		programManagementPage.deleteProgram(program);
	}

	@Step("Delete existing program for the patient")
	public void unenrollPatientFromTheProgram(){
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		String programName = programManagementPage.getProgramFromSpecStore().getName();
		WebElement program = programManagementPage.findProgram(programName);
		programManagementPage.deleteProgram(program);
	}

	@Step("Ensure that the patient is not registered to <program>")
	public void verifyPatientIsNotEnrolledToGivenProgram(String programName) {
		ProgramManagementPage programManagementPage = PageFactory.getProgramManagementPage();
		WebElement program = null;
		try {
			program = programManagementPage.findProgram(programName);
		} catch (Exception e){}
		Assert.assertNull("Patient is enrolled to program "+programName+". Expected not to be", program);
	}

	@Step("Navigate to previous program dashboard")
	public void navigateToProgramDashboard(){
		programManagementPage.clickTreatmentDashboard(programManagementPage.getProgramFromSpecStore());
	}

	@Step("Navigate to <program> program dashboard")
	public void navgateToGivenProgramDashboard(String program){
		programManagementPage.clickTreatmentDashboard(program);
	}

	@Step("Edit created Program with following details <table>")
	public void editCreateProgram(Table table) throws Exception {
		Program program=programManagementPage.getPatientProgramFromSpecStore().getProgram();
		Program program1=transformTableToProgram(table);
		PageFactory.getProgramManagementPage().editProgram(program,program1);
	}

	@Step("Ensure that the program is updated")
	public void verifyProgramUpdated(){
		Program program=programManagementPage.getProgramFromSpecStore();
		PageFactory.getProgramManagementPage().isPatientEnrolledToProgram(program);
	}
	private Program transformTableToProgram(Table table) throws Exception {
		List<TableRow> rows = table.getTableRows();
		List<String> columnNames = table.getColumnNames();
		if (rows.size() != 1) {
			throw new TestSpecException("Only one patient should be provided in the table");
		}
		Program program = programManagementPage.transformTableRowToProgram(rows.get(0), columnNames);

		return program;
	}

	@Step("Ensure that the mentioned program is stopped")
	public void verifyPrgramStopped(){
		programManagementPage.verifyProgramStopped(programManagementPage.getProgramFromSpecStore());
	}




}
