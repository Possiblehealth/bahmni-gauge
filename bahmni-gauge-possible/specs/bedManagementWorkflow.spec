Bed Management Workflow
=======================
Tags: bedManagementWorkflow

* On the login page
* Login as default user on "Location-3"
* Create the following patient using api
    |prefix|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|inpatient|Female|20/01/2011|50|Address1-187204|Darna|Achham|Nepal|2 - Janajati|

* Open "General" visit at "Location-3" for previous patient using api

Admit and verify on adt dashboard and clinical dashboard
--------------------------------------------------------
Tags: regression, sanity, admitAndVerify

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
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Ensure inpatient icon exists on Patient Profile display control
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admission Date<NOW[dd MMM yy]>|
* Verify display control "dispositionSection" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admit Patient<NOW[dd MMM yy]>|

* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Ensure inpatient icon exists on Patient Profile display control
* PreTearDownHook: Discharge patient during teardown

Admit, Transfer and verify on adt dashboard and clinical dashboard
--------------------------------------------------------
Tags: regression, sanity, admitTransferAndVerfiy

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
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Ensure inpatient icon exists on Patient Profile display control
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admission Date<NOW[dd MMM yy]>|
* Verify display control "dispositionSection" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admit Patient<NOW[dd MMM yy]>|

* Select "Transfer Patient" from Patient Movement and click "Transfer" button
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Ensure inpatient icon exists on Patient Profile display control
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admission Date<NOW[dd MMM yy]>|
* Verify display control "dispositionSection" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admit Patient<NOW[dd MMM yy]>|

* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Ensure inpatient icon exists on Patient Profile display control
* PreTearDownHook: Discharge patient during teardown

Admit, Discharge and verify on adt dashboard and clinical dashboard
-------------------------------------------------------------------
Tags: regression, sanity, admitDischargeAndVerfiy

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
* Select "Admit Patient" from Patient Movement and click "Admit" button
* Assign an empty bed
* Navigate to Inpatient Dashboard
* Ensure inpatient icon exists on Patient Profile display control
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admission Date<NOW[dd MMM yy]>|
* Verify display control "dispositionSection" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admit Patient<NOW[dd MMM yy]>|

* Select "Discharge Patient" from Patient Movement and click "Discharge" button
* Select existing patient from patient listing page under tab "All"
* Ensure inpatient icon does not exist on Patient Profile display control
* Verify display control "admissionDetails" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admission Date<NOW[dd MMM yy]>|
    |Discharge Date<NOW[dd MMM yy]>|

* Verify display control "dispositionSection" on inpatient dashboard, has the following details
    |details|
    |-------|
    |Admit Patient<NOW[dd MMM yy]>|

* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "Active"
* Ensure inpatient icon does not exist on Patient Profile display control
* Open the current visit
* Ensure inpatient icon does not exist on Patient Profile display control
* Verify display control "admissionDetails" on dashboard, has the following details
    |details|
    |-------|
    |Admission Date <NOW[dd MMM yy]>|
    |Discharge Date <NOW[dd MMM yy]>|
* PreTearDownHook: Discharge patient during teardown


________________________________________________________________________________

* Navigate to dashboard
* Logout the user
