package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.laatsteWilsbeschikking;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Gemeente;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.time.LocalDate;

import static java.util.Optional.ofNullable;

public record LaatsteWilsbeschikkingJSON(
    String wilsbeschikkingCode,
    WijzeVanLijkbezorging wijzeVanLijkbezorging,
    LocalDate datumVanIndiening,
    Ritueel ritueel,
    LocalDate datumVanVerklaring,
    Gemeente plaatsRegistratie,
    GemeenteEnLandJSON begraafplaats,
    Contract contract
) {
    public record Contract(Organisatie organisatie, String nummer) {}
    public record Organisatie(String kboNummer){}

    public String parsedDatumVanIndiening() {
        return TijdstipParser.parseLocalDate(datumVanIndiening);
    }

    public String parsedDatumVanVerklaring() {
        return TijdstipParser.parseLocalDate(datumVanVerklaring);
    }

    public String contractOrganisatie() {
        return ofNullable(contract)
                .map(item -> item.organisatie)
                .map(org -> org.kboNummer )
                .orElse(null);
    }
    public String contractNummer() {
        return ofNullable(contract)
                .map(item -> item.nummer)
                .orElse(null);
    }

}

