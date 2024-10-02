package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.DossierVerrijkingJSON;

import java.util.Objects;

public record VerrijkingParser(DossierVerrijkingJSON json) {

    public String rrnOverledene() {
        if (Objects.isNull(json)
                || Objects.isNull(json.overledene())) {
            return "-";
        }
        if (!Objects.isNull(json.overledene().rijksregisternummer())) {
            return json.overledene().rijksregisternummer();
        }
        return "-";
    }

    public String rrnMoeder() {
        if (Objects.isNull(json)
                || Objects.isNull(json.moeder())) {
            return "-";
        }
        if(json.moeder().nietGekend()){
            return "Niet gekend";
        }
        if(json.moeder().nietVerblijfshouder()){
            return "Niet-verblijfshouder";
        }
        if (!Objects.isNull(json.moeder().rijksregisternummer())) {
            return json.moeder().rijksregisternummer();
        }
        return "-";
    }

    public String rrnVaderOfMeeMoeder() {
        if (Objects.isNull(json)
                || Objects.isNull(json.vaderOfMeeMoeder())) {
            return "-";
        }
        if(json.vaderOfMeeMoeder().nietVantoepassing()){
            return "Niet van toepassing";
        }
        if (!Objects.isNull(json.vaderOfMeeMoeder().rijksregisternummer())) {
            return json.vaderOfMeeMoeder().rijksregisternummer();
        }
        return "-";
    }

    public String pvOfSysteemNummer() {
        if (Objects.isNull(json)
                || Objects.isNull(json.pvOfSystemNummer())) {
            return "-";
        }
        if(json.pvOfSystemNummer().nietVantoepassing()){
            return "Niet van toepassing";
        }
        return json.pvOfSystemNummer().nummer();
    }




}
