export const VERSLAG_BEEDIGD_ARTS = {
    "@type": "Persoon",
    "naam": "Peeters",
    "voornaam": "Carine",
    "geslacht": "FEMALE",
    "rijksregisternummer": "62650675348",
    "inwonerschap": {
        "@type": "Inwonerschap",
        "verblijfplaats": {
            "@type": "Verblijfplaats",
            "adres": {
                "@type": "Adresvoorstelling",
                "straat": "Grote straat",
                "huisnummer": "20",
                "bus": "1b",
                "postcode": "3500",
                "niscode": "71022"
            }
        }
    },
    "geboorte": {
        "@type": "Geboorte",
        "datum": "1988-03-20T00:00:00",
        "plaats": {
            "andereLocatie": "Hasselt"
        }
    },
    "medischeToestand": {
        "@type": "Overlijdenstoestand",
        "aard": "GewelddadigeOfVerdachteOorzaak",
        "medischeVerslagen": [
            {
                "@type": "MedischVerslagOverlijden",
                "type": "VerslagBeedigdArts",
                "datum": "2023-06-21T23:34",
                "arts": {
                    "@type": "Arts",
                    "registratie": "10079938",
                    "plaatsBeediging": {
                        "@type": "Adresvoorstelling",
                        "niscode": "71022"
                    }
                }
            }
        ]
    },
    "overlijden": {
        "@type": "Overlijden",
        "tijdstip": {
            "@type": "TijdIndicatie",
            "datum": "2023-06-21T23:34",
            "beschrijvingTijdstip": "Vorige week"
        },
        "plaats": [{
            "@type": "Adresvoorstelling",
            "niscode": "71022"
        }]
    }
}