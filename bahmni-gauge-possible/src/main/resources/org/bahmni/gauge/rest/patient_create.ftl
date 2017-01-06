{
    "patient": {
        "person": {
            "names": [
                {
                    "givenName": "${patient.firstName}",
                    "familyName": "${patient.lastName}",
                    "preferred": false
                }
            ],
            "addresses": [
                {
                    "address1": "${patient.ward}",
                    "cityVillage": "${patient.municipality}",
                    "countyDistrict": "${patient.district}",
                    "country": "${patient.country}"
                }
            ],
            "birthdate": "${patient.dateOfBirth}",
            "gender": "${patient.gender}",
            "birthtime": null,
            "attributes": [],
            "deathDate": null,
            "causeOfDeath": ""
        },
        "identifiers": [
            {
                "identifierSourceUuid": "7f74b759-9a45-11e4-9783-005056822b0b",
                "identifierPrefix": "${patient.prefix}",
                "identifierType": "24142e16-28f1-11e4-86a0-005056822b0b",
                "preferred": true,
                "voided": false
            }
        ]
    },
    "relationships": []
}