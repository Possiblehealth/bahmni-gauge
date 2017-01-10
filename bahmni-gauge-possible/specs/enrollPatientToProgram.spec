Program Enrollemnt, Editing of Program Attributes and End the Program
=====================================================================
Tags: enrollPatientToProgram

* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Create the following patient using api
    |prefix|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |--------------------------------------------------------------------------------|
    |BAH|SuperNew|Female|20/01/2011|50|187|Darna|Achham|Nepal|2 - Janajati|

Enrol and varify on programs dashboard
--------------------------------------
Tags: regression, sanity, enrolAndVarify

* Navigate to dashboard
* Click on programs app
* Select existing patient from patient listing page under tab "All"
* Register the patient to following program
    | name      | dateOfRegistration | programStatus                 |
    |----------------------------------------------------------------|
    |ANC Program| 01/01/2015         |ANC Program WorkFlow, 4th Month|
* Ensure that the patient is registered to "ANC Program"

Enrol, Delete and varify on programs dashboard
----------------------------------------------
Tags: regression, sanity, enrolDeleteAndVarify

* Navigate to dashboard
* Click on programs app
* Select existing patient from patient listing page under tab "All"
* Register the patient to following program
    | name      | dateOfRegistration | programStatus                 |
    |----------------------------------------------------------------|
    |ANC Program| 01/01/2015         |ANC Program WorkFlow, 4th Month|
* Ensure that the patient is registered to "ANC Program"
* Unenroll patient from "ANC Program" program
* Ensure that the patient is not registered to "ANC Program"

___________________________________________________________________________

* Navigate to dashboard
* Logout the user
