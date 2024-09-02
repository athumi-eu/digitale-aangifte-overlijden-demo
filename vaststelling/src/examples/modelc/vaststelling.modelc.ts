export const VASTSTELLING = {
    "naam": "Peeters",
    "voornaam": "Carine",
    "geslacht": "FEMALE",
    "medischDossierNummerOverledene": "1234567",
    "rijksregisternummer": "62650675348",
    "zwangerschap": {
        "@type": "Zwangerschap"
    },
    "bevalling": {
        "@type": "OnbekendePersoonsgebeurtenis"
    },
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
    "huwelijk": {
        "@type": "Huwelijk",
        "persoonsGegevens": {
            "@type": "Persoon",
            "voornaam": "Janssens",
            "naam": "Peter"
        }
    },
    "medischeToestand": {
        "@type": "Overlijdenstoestand",
        "aard": "AnderOngeval",
        "beschrijving": "Viel van de trap",
        "ongeval": {
            "@type": "Ongeval",
            "datum": "2023-06-21T23:34",
            "omstandigheden": "Trede kapot",
            "locatie": "Thuis"
        },
        "doodsoorzaak": [
            {
                "@type": "Doodsoorzaak",
                "hoofdOorzaak": "hartfalen",
                "volgtOp": {
                    "@type": "Doodsoorzaak",
                    "hoofdOorzaak": "Diabetes type 2 => heeft  de bloedstroom belemmert en is de oorzaak van het hartfalen",
                    "duur": {
                        "@type": "QuantitativeValue",
                        "waarde": 5,
                        "eenheidcode": "Jaren"
                    },
                    "volgtOp": {
                        "@type": "Doodsoorzaak",
                        "hoofdOorzaak": "Obesitas door een ongezonde levensstijl.",
                        "duur": {
                            "@type": "QuantitativeValue",
                            "waarde": 10,
                            "eenheidcode": "Jaren"
                        }
                    }
                }
            },
            {
                "@type": "Doodsoorzaak",
                "geassocieerdeOorzaak": "atherosclerose",
                "duur": {
                    "@type": "QuantitativeValue",
                    "waarde": 12,
                    "eenheidcode": "Maanden"
                }
            }
        ],
        "medischeVerslagen": [
            {
                "@type": "MedischVerslagOverlijden",
                "type": "VaststellingOverlijden",
                "bezwaar": [
                    "BezwaarTegenCrematie"
                ],
                "risico": [
                    "BlootstellingIoniserendeStralen"
                ],
                "maatregel": [
                    "VerplichteOnmiddellijkeKistingHermetisch"
                ],
                "datum": "2023-06-21T23:34",
                "arts": {
                    "@type": "Arts",
                    "registratie": "10079938",
                    "type": [
                        "Huisarts",
                        "ArtsVanVaststelling"
                    ]
                }
            },
            {
                "@type": "MedischVerslagOverlijden",
                "type": "AutopsieVerslag",
                "status": "Lopend"
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
        "plaats": [
            {
                "@type": "Adresvoorstelling",
                "straat": "Grote straat",
                "huisnummer": "20",
                "bus": "1b",
                "postcode": "3500",
                "niscode": "71022"
            },
            {
                "@type": "Adresvoorstelling",
                "locatie": "Andere",
                "andereLocatie": "op straat"
            }
        ]
    }
};