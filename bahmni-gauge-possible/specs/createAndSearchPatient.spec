Create Possible Patient Scenarios
=================================
Tags: createAndSearchPatient

Create Patient with manual ID, Verify
-------------------------------------

Tags:

* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Click on registration app
* Click on create new patient link
* Create patient with manual id
    |prefix|idNumber|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|########|Super|Female|20/01/2011|50|Address1-187204|Darna|Achham|Nepal|2 - Janajati|

* Click on search patient link
* Search previously created patient with exact identifier
* Ensure that the patient edit page is opened for previously created patient
* Logout the user

Create Patient with autoID, Verify
----------------------------------

Tags: regression, sanity, smoke

* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Click on registration app
* Click on create new patient link
* Create the following patient
    |prefix|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|Himangi|Female|20/01/2011|50|Address1-187204|Darna|Achham|Nepal|2 - Janajati|

* Click on search patient link
* Search previously created patient with name
* Ensure that the patient edit page is opened for previously created patient
* Logout the user


Create Patient fails with same ID
---------------------------------

Tags:

* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Click on registration app
* Click on create new patient link
* Create patient with manual id
    |prefix|idNumber|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|8888881|Super|Female|20/01/2011|50|Address1-187204|Darna|Achham|Nepal|2 - Janajati|

* Verify the patient creation fails
* Logout the user


SEARCH PATIENT WITH FILTERS
---------------------------

Tags: regression, sanity, smoke

* Create possible patient through API
* On the login page
* Login with username "BAHMNI_GAUGE_APP_USER" and password "BAHMNI_GAUGE_APP_PASSWORD" with location "BAHMNI_GAUGE_APP_LOCATION"
* Click on registration app
* Click on search patient link
* Search previously created patient with name
* Select the patient from the search results
* Ensure that the patient edit page is opened for previously created patient
* Logout the user