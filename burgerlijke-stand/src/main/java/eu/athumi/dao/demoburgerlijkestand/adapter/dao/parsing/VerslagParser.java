package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.BeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.MedischVerslagBeedigdArtsJSON;
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

    public String geboortePlaats() {
        if (Objects.isNull(verslag.geboorte())
                || Objects.isNull(verslag.geboorte().plaats())
                || Objects.isNull(verslag.geboorte().plaats().locatie())) {
            return "";
        }
        return verslag.geboorte().plaats().locatie();
    }

    public String geboorteDatum() {
        if (Objects.isNull(verslag.geboorte())) {
            return "";
        }
        return verslag.geboorte().datum();
    }

    public String verblijfplaatsOverledene() {
        if (Objects.isNull(verslag.inwonerschap())
                || Objects.isNull(verslag.inwonerschap().verblijfplaats())
                || Objects.isNull(verslag.inwonerschap().verblijfplaats().adres())) {
            return null;
        }
        return PlaatsParser.parseAdres(verslag.inwonerschap().verblijfplaats().adres());
    }

    public String datumOverlijden() {
        if (Objects.isNull(verslag.overlijden())) {
            return "";
        }
        return TijdstipParser.parseTijdstip(verslag.overlijden().tijdstip());
    }

    public String gemeenteOverlijden() {
        if (Objects.isNull(verslag.overlijden())
                || Objects.isNull(verslag.overlijden().plaats())
                || verslag.overlijden().plaats().isEmpty()) {
            return "";
        }
        return PlaatsParser.parseAdres(verslag.overlijden().plaats().getFirst());
    }

    public String aardOverlijden() {
        if (Objects.isNull(verslag.medischeToestand())
                || Objects.isNull(verslag.medischeToestand().aard())) {
            return "";
        }
        return verslag.medischeToestand().aard().toString();
    }

    public String controleArts() {
        var arts = getArts(verslag);
        if (Objects.isNull(arts)) {
            return "";
        }
        var naam = Objects.isNull(arts.naam()) ? "" : arts.naam();
        var voornaam = Objects.isNull(arts.voornaam()) ? "" : arts.voornaam();
        var riziv = Objects.isNull(arts.registratie()) ? "" : arts.registratie();
        return naam + " " + voornaam + " (" + riziv + ")";
    }

    public String gemeenteBeediging() {
        var arts = getArts(verslag);
        if (Objects.isNull(arts)
                || Objects.isNull(arts.plaatsBeediging())
                || Objects.isNull(arts.plaatsBeediging().niscode())) {
            return "";
        }
        return "niscode: " + arts.plaatsBeediging().niscode();
    }

    public String aanmaakDatum() {
        var medischVerslag = getMedischVerslag(verslag);
        if (Objects.isNull(medischVerslag)) {
            return "";
        }
        return TijdstipParser.parseLocalDateTime(medischVerslag.datum());
    }

    private BeedigdArtsJSON getArts(VerslagBeedigdArtsJSON verslag) {
        if (Objects.isNull(verslag.medischeToestand())
                || Objects.isNull(verslag.medischeToestand().medischeVerslagen())) {
            return null;
        }
        var medischVerslag = getMedischVerslag(verslag);
        if (Objects.isNull(medischVerslag)
                || Objects.isNull(medischVerslag.arts())) {
            return null;
        }
        return medischVerslag.arts();
    }

    private MedischVerslagBeedigdArtsJSON getMedischVerslag(VerslagBeedigdArtsJSON verslag) {
        if (Objects.isNull(verslag.medischeToestand())
                || Objects.isNull(verslag.medischeToestand().medischeVerslagen())) {
            return null;
        }
        return verslag.medischeToestand().medischeVerslagen().stream().filter(mv -> Objects.equals(mv.type(), "MedischVerslagOverlijden")).findFirst().orElse(null);
    }


}
