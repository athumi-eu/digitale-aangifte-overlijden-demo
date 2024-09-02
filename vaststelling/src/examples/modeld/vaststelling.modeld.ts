export const VASTSTELLING = {
    "naam": "Vermeulen",
    "voornaam": "Jeanne",
    "geslacht": "FEMALE",
    "geboorte": {
        "@type": "Geboorte",
        "datum": "1988-02-02T10:10:00",
        "plaats": {
            "@type": "Adresvoorstelling",
            "locatie": "Ziekenhuis",
            "andereLocatie": "Hasselt"
        },
        "geboorteToestand": {
            "@type": "GeboorteToestand",
            "rang": "1",
            "aandoening": [
                "Anencefalie",
                "Hydrocefalie"
            ],
            "andereAandoening": "andere aandoening",
            "geboortegewicht": {
                "@type": "KwantitatieveWaarde",
                "eenheidcode": "Gram",
                "waarde": 3.4
            },
            "apgarScore": [
                {
                    "periode": "EenMinuut",
                    "score": 1
                },
                {
                    "periode": "VijfMinuten",
                    "score": 5
                },
                {
                    "periode": "TienMinuten",
                    "score": 10
                }
            ],
            "zorgen": [
                {
                    "@type": "Procedure",
                    "code": "Intubatie",
                    "status": "Ja"
                },
                {
                    "@type": "Procedure",
                    "code": "Andere",
                    "status": "Ja"
                },
                {
                    "@type": "Procedure",
                    "code": "Ongekend",
                    "status": "NietVanToepassing"
                }
            ],
            "andereZorgen": "Omschrijving andere zorg"
        }
    },
    "moeder": {
        "@type": "MoederRelatie",
        "gezinsrelatietype": "Moeder",
        "persoonsGegevens": {
            "@type": "Persoon",
            "voornaam": "Jeanine",
            "naam": "Vermeulen",
            "inwonerschap": {
                "@type": "Inwonerschap",
                "verblijfplaats": {
                    "@type": "Verblijfplaats",
                    "adres": {
                        "@type": "Adresvoorstelling",
                        "straat": "Diestsesteenweg",
                        "huisnummer": "11",
                        "bus": "11b",
                        "postcode": "3000",
                        "niscode": "24062"
                    }
                }
            },
            "zwangerschap": {
                "@type": "Zwangerschap",
                "zwangerschapToestand": {
                    "@type": "ZwangerschapToestand",
                    "duur": 12,
                    "risico": [
                        "Diabetes",
                        "Eclampsie"
                    ],
                    "anderRisico": "een ander risico",
                    "ligging": [
                        "Andere",
                        "Stuit"
                    ],
                    "andereLigging": "andere ligging",
                    "zorgen": [
                        {
                            "@type": "Procedure",
                            "code": "Transfer",
                            "status": "Ja"
                        }
                    ]
                }
            },
            "bevalling": {
                "@type": "Bevalling",
                "bevallingToestand": {
                    "@type": "BevallingToestand",
                    "indicatiesBevallingTypeBijKind": [
                        "Andere"
                    ],
                    "andereIndicatiesBevallingTypeBijKind": "Een andere indicatie voor kind",
                    "indicatiesBevallingTypeBijMoeder": [
                        "Dystocie"
                    ],
                    "andereIndicatiesBevallingTypeBijMoeder": "Een andere indicatie voor moeder",
                    "assistentieWijze": [
                        "Forceps"
                    ],
                    "andereAssistentieWijze": "Een andere assistentie wijze",
                    "meerling": "Ja",
                    "aantalGeboorten": 6,
                    "aantalLevendGeboren": {
                        "mannelijk": 1,
                        "vrouwelijk": 1,
                        "onbepaald": 1
                    },
                    "aantalDoodgeboren": {
                        "mannelijk": 1,
                        "vrouwelijk": 1,
                        "onbepaald": 1
                    },
                    "aandoening": [
                        {
                            "type": "Ademnood",
                            "aanwezig": "NietVanToepassing",
                            "omschrijving": "Omschrijving ademnood"
                        }
                    ],
                    "zorgen": [
                        {
                            "@type": "Procedure",
                            "code": "Inductie",
                            "status": "Ja"
                        },
                        {
                            "@type": "Procedure",
                            "code": "Assistentie",
                            "status": "Ja"
                        }
                    ]
                }
            }
        },
        "vorigeGeboorten": {
            "@type": "VorigeGeboorten",
            "aantalKinderenNogInLeven": {
                "@type": "QuantitativeValue",
                "waarde": 2
            },
            "aantalLevendGeboren": {
                "@type": "QuantitativeValue",
                "waarde": 3
            },
            "aantalDoodGeboren": {
                "@type": "QuantitativeValue",
                "waarde": 1
            },
            "datumVorigeGeboorte": "2019-06-21T23:34"
        }
    },
    "medischeToestand": {
        "@type": "Overlijdenstoestand",
        "aard": "AnderOngeval",
        "beschrijving": "Viel van de trap",
        "ongeval": {
            "@type": "Ongeval",
            "datum": "2023-06-21T23:34",
            "omstandigheden": "Het zag er niet zo schoon uit",
            "locatie": "Thuis"
        },
        "doodsoorzaak": [
            {
                "@type": "Doodsoorzaak",
                "hoofdOorzaak": "hartfalen",
                "gerelateerdAan": [
                    "Moeder",
                    "Foetus",
                    "Kind"
                ],
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
                "gerelateerdAan": [
                    "Moeder"
                ],
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
                "datum": "2023-06-21T23:34",
                "arts": {
                    "@type": "Arts",
                    "registratie": "123456",
                    "type": [
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