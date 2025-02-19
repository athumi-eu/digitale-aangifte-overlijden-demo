export const ENKEL_BEGRAAFPLAATS = {
    "begraafplaats": {
        "niscodeGemeente": "24062"
    }
}

export const BEGRAAFPLAATS_ANDERE_LOCATIE = {
    "begraafplaats": {
        "andereLocatie": "andereLocatie"
    }
}

export const ANDER_CREMATORIUM = {
    "crematie": {
        "anderCrematorium": "anderCrematorium",
        "bestemmingAs": {
            "bestemmingAsType": "BEGRAVING_URNE",
            "locatieType": "BEGRAAFPLAATS",
            "locatie": "Een locatie via hint",
            "nabestaande": null,
            "documenten": []
        },
        "asWettelijkePartner": {
            "type": "NIET_VAN_TOEPASSING"
        }
    }
}

export const CREMATORIUM_BELGIE = {
    "crematie": {
        "crematoriumInBelgie": "a00a44aa-332d-4a0e-ac3c-12ec97715c37",
        "bestemmingAs": {
            "bestemmingAsType": "BEGRAVING_URNE",
            "locatieType": "BEGRAAFPLAATS",
            "locatie": "Een locatie via hint",
            "nabestaande": null,
            "documenten": []
        },
        "asWettelijkePartner": {
            "type": "NIET_VAN_TOEPASSING"
        }
    }
}

export const PRIVATE_BEGRAVING = {
    "crematie": {
        "crematoriumInBelgie": "a00a44aa-332d-4a0e-ac3c-12ec97715c37",
        "bestemmingAs": {
            "bestemmingAsType": "BEGRAVING_URNE",
            "locatieType": "PRIVAAT_DOMEIN",
            "locatie": "Een locatie via hint",
            "nabestaande": {
                "rijksregisternummer": "62650675348",
                "voornaam": "Voornaam nabestaande",
                "naam": "Naam nabestaande",
                "adresBelgie": {
                    "straat": "straat",
                    "huisnummer": "1",
                    "gemeenteNaam": "Leuven",
                    "postcode": "3500",
                    "niscode": "71022"
                },
                "documenten": []
            }
        },
        "asWettelijkePartner": {
            "type": "NIET_VAN_TOEPASSING"
        }
    }
}

export const MET_AS_WETTELIJKE_PARTNER = {
    "crematie": {
        "crematoriumInBelgie": "a00a44aa-332d-4a0e-ac3c-12ec97715c37",
        "bestemmingAs": {
            "bestemmingAsType": "BEGRAVING_URNE",
            "locatieType": "OPENBAAR_DOMEIN",
            "locatie": "Een locatie via hint",
            "nabestaande": {
                "rijksregisternummer": "62650675348",
                "voornaam": "Voornaam nabestaande",
                "naam": "Naam nabestaande",
                "adresBelgie": {
                    "straat": "straat",
                    "huisnummer": "1",
                    "gemeenteNaam": "Leuven",
                    "postcode": "3500",
                    "niscode": "71022"
                },
                "documenten": []
            }
        },
        "asWettelijkePartner": {
            "type": "SAMEN_MET_DE_DOODSKIST_BEGRAVEN",
            "rijksregisternummer": "81.70.22-996-50",
            "voornaam": "Justine",
            "naam": "Janssens",
            "geboorteDatum": "1981-10-22",
            "datumOverlijden": "1996-06-01",
            "plaatsOverlijden": "Kortenberg"
        }
    }
}

export const LEVENLOOS_KIND = {
    "crematie": {
        "crematoriumInBelgie": "a00a44aa-332d-4a0e-ac3c-12ec97715c37",
        "bestemmingAs": {
            "bestemmingAsType": "BEGRAVING_URNE",
            "locatieType": "PRIVAAT_DOMEIN",
            "locatie": "Een locatie via hint",
            "nabestaande": {
                "rijksregisternummer": "62650675348",
                "voornaam": "Voornaam nabestaande",
                "naam": "Naam nabestaande",
                "adresBelgie": {
                    "straat": "straat",
                    "huisnummer": "1",
                    "gemeenteNaam": "Leuven",
                    "postcode": "3500",
                    "niscode": "71022"
                },
                "documenten": []
            }
        },
        "asWettelijkePartner": {
            "type": "SAMEN_MET_DE_DOODSKIST_BEGRAVEN",
            "rijksregisternummer": "81.70.22-996-50",
            "voornaam": "Justine",
            "naam": "Janssens",
            "geboorteDatum": "1981-10-22",
            "datumOverlijden": "1996-06-01",
            "plaatsOverlijden": "Kortenberg"
        }
    },
    "informatieAkteLevenloosKind": {
        "vaderOfMeemoeder": {
            "rijksregisternummer": "62650675348",
            "voornaam":  "Voornaam meemoeder",
            "naam": "Achternaam meemoeder",
            "geboorteDatum":  "1981-10-22",
            "geboortelocatie": "Gent"
        }
    }
}

export const BESTEMMING_EN_AS_WETTELIJKE_PARTNER ={
    "crematie": {
        "crematoriumInBelgie": "a00a44aa-332d-4a0e-ac3c-12ec97715c37",
        "bestemmingAs": {
            "bestemmingAsType": "BEGRAVING_URNE",
            "locatieType": "PRIVAAT_DOMEIN",
            "locatie": "Een locatie via hint",
            "nabestaande": {
                "rijksregisternummer": "62650675348",
                "voornaam": "Voornaam nabestaande",
                "naam": "Naam nabestaande",
                "adresBelgie": {
                    "straat": "straat",
                    "huisnummer": "1",
                    "gemeenteNaam": "Leuven",
                    "postcode": "3500",
                    "niscode": "71022"
                },
                "documenten": []
            }
        },
        "asWettelijkePartner": {
            "type": "SAMEN_MET_DE_DOODSKIST_BEGRAVEN",
            "rijksregisternummer": "81.70.22-996-50",
            "voornaam": "Justine",
            "naam": "Janssens",
            "geboorteDatum": "1981-10-22",
            "datumOverlijden": "1996-06-01",
            "plaatsOverlijden": "Kortenberg"
        }
    }
}