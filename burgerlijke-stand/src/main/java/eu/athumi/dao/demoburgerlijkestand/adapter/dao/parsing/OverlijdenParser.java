package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.VaststellingOverlijdenJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenJSON;

import java.util.Objects;

public record OverlijdenParser(OverlijdenJSON overlijden, VaststellingOverlijdenJSON vaststellingOverlijdenJSON) {

    public String tijdstipOverlijden() {
        if (Objects.isNull(overlijden)
                || Objects.isNull(overlijden.tijdstip())) {
            return "/";
        }
        return TijdstipParser.parseTijdstip(overlijden.tijdstip());
    }

    public String tijdstipVaststelling() {
        if (Objects.isNull(vaststellingOverlijdenJSON)) {
            return "/";
        }
        return TijdstipParser.parseLocalDateTime(vaststellingOverlijdenJSON.datum());
    }

    public String artsVaststelling() {
        if (Objects.isNull(vaststellingOverlijdenJSON)) {
            return "/";
        } else if (Objects.isNull(vaststellingOverlijdenJSON.arts())) {
            return "/";
        }
        var naam = Objects.isNull(vaststellingOverlijdenJSON.arts().naam()) ? "" : vaststellingOverlijdenJSON.arts().naam();
        var voornaam = Objects.isNull(vaststellingOverlijdenJSON.arts().voornaam()) ? "" : vaststellingOverlijdenJSON.arts().voornaam();
        var riziv = Objects.isNull(vaststellingOverlijdenJSON.arts().registratie()) ? "" : vaststellingOverlijdenJSON.arts().registratie();
        return naam + " " + voornaam + " (" + riziv + ")";
    }

    public String adresOverlijden() {
        if (Objects.isNull(overlijden)
                || Objects.isNull(overlijden.getAdresOverlijden())) {
            return "/";
        }
        return PlaatsParser.parseAdres(overlijden.getAdresOverlijden());
    }

    public String plaatsOverlijden() {
        if (Objects.isNull(overlijden)
                || Objects.isNull(overlijden.getLocatieOverlijden())) {
            return "/";
        }
        return PlaatsParser.parseLocatie(overlijden.getLocatieOverlijden());
    }

    public String bewijzen(String type) {
        if (Objects.isNull(overlijden)
                || Objects.isNull(overlijden.getOverlijdenAfhandeling(type))) {
            return "/";
        }
        return OverlijdenAfhandelingParser.parseOverlijdenAfhandeling(overlijden.getOverlijdenAfhandeling(type));
    }
}
