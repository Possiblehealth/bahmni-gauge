Admit Patient
=================================
Tags: admitPatient

Create Patient and perform Inpatient Flow
---------------------------------------------------
Tags: regression, sanity, admit

* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Verify Login Page
* Click on registration app
* Click on create new patient link
* Create the following patient
    |prefix|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|inpatient|Female|20/01/2011|50|Address1-187204|Darna|Achham|Nepal|2 - Janajati|
* Start a visit "General"
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Navigate to consultation
* Go to "Disposition" tab
* Select "Admit Patient" disposition
* Save the consultation
* Navigate to dashboard
* Click on inpatient app
* Select existing patient from patient listing page under tab "To Admit"
* Navigate to dashboard
* Logout the user