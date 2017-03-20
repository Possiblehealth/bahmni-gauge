Create And View Drug Orders
===========================
Tags: createAndViewDrugOrders

Create Patient And Add Drug Orders
----------------------------------

Tags: regression, sanity

* On the login page
* Login as default user on "Location-3"
* Click on registration app
* Click on create new patient link
* Create the following patient
    |prefix|firstName|gender|dateOfBirth|age|ward|municipality|district|country|caste|
    |BAH|Automation|Female|20/01/2011|50|187|Darna|Achham|Nepal|2 - Janajati|

* Start a visit "General"
* Navigate to dashboard
* Click on clinical app
* Select existing patient from patient listing page under tab "All"
* Navigate to consultation
* Go to "Treatment" tab
* Create drug order
    |drugName|dose|doseUnit|frequency|route|additionalInstructions|duration|durationUnit|
    |Paracetamol|2|mg|Immediately|Oral|Take if Needed|2| Day(s)|
    |Isoniazid (H)|4|mg|Immediately|Oral|Take if Needed|2| Day(s)|
* Save the consultation
* Navigate to patient dashboard
* Verify display control "Treatments" on dashboard, has the following details

    |details|
    |Paracetamol 2 mg, Immediately - 2 Day(s)|
    |Isoniazid (H) 4 mg, Immediately - 2 Day(s)|

* Navigate to dashboard
* Logout the user
