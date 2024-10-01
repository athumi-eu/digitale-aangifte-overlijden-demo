package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.DossierVerrijkingJSON;

import java.util.Objects;

public record VerrijkingParser(DossierVerrijkingJSON json) {

    public String rrnOverledene() {
        if (Objects.isNull(json)
                || Objects.isNull(json.overledene())) {
            return "-";
        }
        return json.overledene().rijksregisternummer();
    }

    public String rrnMoeder() {
        if (Objects.isNull(json)
                || Objects.isNull(json.moeder())) {
            return "-";
        }
        return json.moeder().rijksregisternummer();
    }

    public String rrnVaderOfMeeMoeder() {
        if (Objects.isNull(json)
                || Objects.isNull(json.vaderOfMeeMoeder())) {
            return "-";
        }
        return json.vaderOfMeeMoeder().rijksregisternummer();
    }

    public String pvOfSysteemNummer() {
        if (Objects.isNull(json)
                || Objects.isNull(json.pvOfSystemNummer())) {
            return "-";
        }
        return json.pvOfSystemNummer().nummer();
    }




}
