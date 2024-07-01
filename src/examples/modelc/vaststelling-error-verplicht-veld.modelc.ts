export const VASTSTELLING_ERROR_VERPLICHT_VELD = {
    "naam": "Peeters",
    "voornaam": "Carine",
    "geslacht": "FEMALE",
    "medischeToestand": {
        "@type": "Overlijdenstoestand",
        "aard": "AnderOngeval",
        "doodsoorzaak": [
            {
                "@type": "Doodsoorzaak",
                "hoofdOorzaak": "hartfalen"
            }
        ],
        "medischeVerslagen": [
            {
                "@type": "MedischVerslagOverlijden",
                "type": "VaststellingOverlijden",
                "datum": "2023-06-21T23:34",
                "arts": {
                    "@type": "Arts",
                    "registratie": "10079938",
                    "type": [
                        "Huisarts",
                        "ArtsVanVaststelling"
                    ]
                }
            }
        ]
    },
    "overlijden": {
        "@type": "Overlijden",
        "tijdstip": {
            "@type": "TijdIndicatie",
            "bekend": true,
            "datum": "2023-06-21T23:34",
            "beschrijvingTijdstip": "Vorige week"
        },
        "plaats": {
            "@type": "Adresvoorstelling",
            "straat": "Grote straat",
            "huisnummer": "20",
            "bus": "1b",
            "postcode": "3500",
            "niscode": "71022",
            "locatie": "Andere",
            "andereLocatie": "binnen in huis"
        }
    }
};