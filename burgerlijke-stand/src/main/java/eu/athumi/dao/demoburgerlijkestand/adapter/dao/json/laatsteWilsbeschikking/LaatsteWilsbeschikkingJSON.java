package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.laatsteWilsbeschikking;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Gemeente;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Land;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Optional.ofNullable;

public record LaatsteWilsbeschikkingJSON(
        String wilsbeschikkingCode,
        WijzeVanLijkbezorging wijzeVanLijkbezorging,
        LocalDate datumVanIndiening,
        Ritueel ritueel,
        LocalDate datumVanVerklaring,
        Gemeente plaatsRegistratie,
        Begraafplaats begraafplaats,
        Contract contract
) {
    public record Contract(Organisatie organisatie, String nummer, LocalDate datum) {
    }

    public record Organisatie(String kboNummer) {
    }

    public record Begraafplaats(Gemeente gemeente, Land land, String beschrijving) {
        @Override
        public String toString() {
            return  (beschrijving != null ? " " + beschrijving + " - " : "") + (gemeente != null ? gemeente().naam() : "") + (land == null || Objects.equals(land.naam(), "BELGIE") ? "" : " (" + land.naam() + ")");
        }
    }

    public String parsedDatumVanIndiening() {
        return TijdstipParser.parseLocalDate(datumVanIndiening);
    }

    public String parsedDatumVanVerklaring() {
        return TijdstipParser.parseLocalDate(datumVanVerklaring);
    }

    public String parsedDatumVanContract() {
        return ofNullable(contract).map(item -> item.datum)
                .map(TijdstipParser::parseLocalDate)
                .orElse(null);
    }

    public String contractOrganisatie() {
        return ofNullable(contract)
                .map(item -> item.organisatie)
                .map(org -> org.kboNummer)
                .orElse(null);
    }

    public String contractNummer() {
        return ofNullable(contract)
                .map(item -> item.nummer)
                .orElse(null);
    }

}

