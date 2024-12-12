package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche.VaderOfMeemoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.DossierVerrijkingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.MoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.OverledeneJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.VaderOfMeeMoederJSON;

import java.util.Objects;
import java.util.Optional;

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

    public String naamOverledene() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::overledene)
                .map(overledene -> String.format("%s %s", overledene.voornaam(), overledene.naam()))
                .orElse("-");
    }

    public String geslachtOverledene() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::overledene)
                .map(OverledeneJSON::geslacht)
                .map(Enum::name)
                .orElse("-");
    }

    public String geboorteDatumOverledene() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::overledene)
                .map(OverledeneJSON::geboorteDatum)
                .map(TijdstipParser::parseLocalDate)
                .orElse("-");
    }

    public String rrnMoeder() {
        if (Objects.isNull(json)
                || Objects.isNull(json.moeder())) {
            return "-";
        }
        if (json.moeder().nietGekend()) {
            return "Niet gekend";
        }
        if (json.moeder().nietVerblijfshouder()) {
            return "Niet-verblijfshouder";
        }
        if (!Objects.isNull(json.moeder().rijksregisternummer())) {
            return json.moeder().rijksregisternummer();
        }
        return "-";
    }

    public String naamMoeder() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::moeder)
                .map(overledene -> String.format("%s %s", overledene.voornaam(), overledene.naam()))
                .orElse("-");
    }

    public String geslachtMoeder() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::moeder)
                .map(MoederJSON::geslacht)
                .map(Enum::name)
                .orElse("-");
    }

    public String geboorteDatumMoeder() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::moeder)
                .map(MoederJSON::geboorteDatum)
                .map(TijdstipParser::parseLocalDate)
                .orElse("-");
    }

    public String rrnVaderOfMeeMoeder() {
        if (Objects.isNull(json)
                || Objects.isNull(json.vaderOfMeeMoeder())) {
            return "-";
        }
        if (json.vaderOfMeeMoeder().nietVantoepassing()) {
            return "Niet van toepassing";
        }
        if (!Objects.isNull(json.vaderOfMeeMoeder().rijksregisternummer())) {
            return json.vaderOfMeeMoeder().rijksregisternummer();
        }
        return "-";
    }

    public String naamVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::vaderOfMeeMoeder)
                .map(overledene -> String.format("%s %s", overledene.voornaam(), overledene.naam()))
                .orElse("-");
    }

    public String geslachtVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::vaderOfMeeMoeder)
                .map(VaderOfMeeMoederJSON::geslacht)
                .map(Enum::name)
                .orElse("-");
    }

    public String geboorteDatumVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(DossierVerrijkingJSON::vaderOfMeeMoeder)
                .map(VaderOfMeeMoederJSON::geboorteDatum)
                .map(TijdstipParser::parseLocalDate)
                .orElse("-");
    }

    public String pvOfSysteemNummer() {
        if (Objects.isNull(json)
                || Objects.isNull(json.pvOfSystemNummer())) {
            return "-";
        }
        if (json.pvOfSystemNummer().nietVantoepassing()) {
            return "Niet van toepassing";
        }
        return json.pvOfSystemNummer().nummer();
    }


}
