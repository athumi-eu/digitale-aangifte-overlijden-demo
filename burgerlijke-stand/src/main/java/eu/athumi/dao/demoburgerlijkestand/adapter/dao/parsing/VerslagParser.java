package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;

import java.util.Objects;

public record VerslagParser(VerslagBeedigdArtsJSON verslag) {


    public String naamOverledene() {
        var naam = Objects.isNull(verslag.naam()) ? "" : verslag.naam();
        var voornaam = Objects.isNull(verslag.voornaam()) ? "" : verslag.voornaam();
        return naam + " " + voornaam;
    }
    public String rijksregisternummer() {
        return Objects.isNull(verslag.rijksregisternummer()) ? "-" : verslag.rijksregisternummer();
    }

    public String id() {
        return verslag.id();
    }

    public String geslacht() {
        return verslag.geslacht().toString();
    }

    public String datumOverlijden() {
        if(Objects.isNull(verslag.overlijden())) {
            return "";
        }
        return TijdstipParser.parseTijdstip(verslag.overlijden().tijdstip());
    }

    public String geboortePlaats() {
        if(Objects.isNull(verslag.geboorte())
        || Objects.isNull(verslag.geboorte().plaats())
                || Objects.isNull(verslag.geboorte().plaats().locatie())) {
            return "";
        }
        return verslag.geboorte().plaats().locatie();
    }

    public String geboorteDatum() {
        if(Objects.isNull(verslag.geboorte())) {
            return "";
        }
        return TijdstipParser.parseLocalDateTime(verslag.geboorte().datum());
    }

    public String verblijfplaatsOverledene() {
        if(Objects.isNull(verslag.inwonerschap())
        || Objects.isNull(verslag.inwonerschap().verblijfplaats())
        || Objects.isNull(verslag.inwonerschap().verblijfplaats().adres())) {
            return "";
        }
        return PlaatsParser.parseAdres(verslag.inwonerschap().verblijfplaats().adres());
    }



}
