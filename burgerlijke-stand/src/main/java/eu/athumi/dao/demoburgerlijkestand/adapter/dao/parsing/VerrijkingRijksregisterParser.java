package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.rijksregister.VerrijkingRijksregisterJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.rijksregister.VerrijkingRijksregisterPersoonJSON;

import java.util.Optional;

public class VerrijkingRijksregisterParser {

    private final VerrijkingRijksregisterJSON json;

    public VerrijkingRijksregisterParser(VerrijkingRijksregisterJSON json) {
        this.json = json;
    }

    public String rrnOverledene() {
        return Optional.ofNullable(json.overledene()).map(VerrijkingRijksregisterPersoonJSON::rijksregisternummer).orElse("-");
    }

    public String rrnMoeder() {
        return Optional.ofNullable(json.moederOfOudsteOuder()).map(VerrijkingRijksregisterPersoonJSON::rijksregisternummer).orElse("-");
    }

    public String rrnVaderOfMeeMoeder() {
        return Optional.ofNullable(json.vaderOfJongsteOuder()).map(VerrijkingRijksregisterPersoonJSON::rijksregisternummer).orElse("-");
    }

    public String naamOverledene() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::overledene)
                .map(overledene -> String.format("%s %s", overledene.voornaam(), overledene.naam()))
                .orElse("-");
    }

    public String geslachtOverledene() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::overledene)
                .map(VerrijkingRijksregisterPersoonJSON::geslacht)
                .map(Enum::name)
                .orElse("-");
    }

    public String geboorteDatumOverledene() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::overledene)
                .map(VerrijkingRijksregisterPersoonJSON::geboortedatum)
                .map(TijdstipParser::parseDateString)
                .orElse("-");
    }

    public boolean hasMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::moederOfOudsteOuder)
                .map(VerrijkingRijksregisterPersoonJSON::rijksregisternummer).isPresent();
    }

    public String naamMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::moederOfOudsteOuder)
                .map(overledene -> String.format("%s %s", overledene.voornaam(), overledene.naam()))
                .orElse("-");
    }

    public String geslachtMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::moederOfOudsteOuder)
                .map(VerrijkingRijksregisterPersoonJSON::geslacht)
                .map(Enum::name)
                .orElse("-");
    }

    public String geboorteDatumMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::moederOfOudsteOuder)
                .map(VerrijkingRijksregisterPersoonJSON::geboortedatum)
                .map(TijdstipParser::parseDateString)
                .orElse("-");
    }

    public boolean hasVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::vaderOfJongsteOuder)
                .map(VerrijkingRijksregisterPersoonJSON::rijksregisternummer).isPresent();
    }

    public String naamVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::vaderOfJongsteOuder)
                .map(overledene -> String.format("%s %s", overledene.voornaam(), overledene.naam()))
                .orElse("-");
    }

    public String geslachtVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::vaderOfJongsteOuder)
                .map(VerrijkingRijksregisterPersoonJSON::geslacht)
                .map(Enum::name)
                .orElse("-");
    }

    public String geboorteDatumVaderOfMeeMoeder() {
        return Optional.ofNullable(json)
                .map(VerrijkingRijksregisterJSON::vaderOfJongsteOuder)
                .map(VerrijkingRijksregisterPersoonJSON::geboortedatum)
                .map(TijdstipParser::parseDateString)
                .orElse("-");
    }
}
