package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.MoederOfOudsteOuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.MoederVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.PlaatsParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public record OuderParser(OuderJSON ouder, MoederVaststellingJSON moederVaststelling) {

    public boolean isExtended() {
        return ouder instanceof MoederOfOudsteOuderJSON;
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                "-",
                ofNullable(moederVaststelling).map(MoederVaststellingJSON::geslacht).map(Geslacht::name).orElse("-"),
                "-",
                "-",
                ofNullable(ouder.geslacht()).map(Geslacht::name).orElse("-")
        );
    }

    public TableRow geboorteDatum() {
        return new TableRow(
                "Geboortedatum",
                "-",
                "-",
                "-",
                "-",
                ofNullable(ouder.geboorte()).map(GeboorteJSON::datum).map(TijdstipParser::parseLocalDate).orElse("-")
        );
    }

    public TableRow huidigeNationaliteit() {
        return new TableRow(
                "Huidige nationaliteit",
                "-",
                "-",
                "-",
                "-",
                ofNullable(ouder.nationaliteit()).map(NationaliteitJSON::naam).orElse("-")
        );
    }

    public TableRow oorspronkelijkeNationaliteit() {
        return new TableRow(
                "Oorspronkelijke nationaliteit(en)",
                "-",
                "-",
                "-",
                "-",
                ofNullable(ouder.oorspronkelijkeNationaliteit()).map(nationaliteiten ->
                                nationaliteiten.stream()
                                        .map(NationaliteitJSON::naam)
                                        .collect(Collectors.joining(", ")))
                        .orElse("-")
        );
    }

    public TableRow verblijfplaats() {
        if (!isExtended()) {
            return TableRow.empty();
        }
        return new TableRow(
                "Verblijfplaats",
                "-",
                PlaatsParser.getVerblijfplaatsVoorOuder(moederVaststelling),
                "-",
                "-",
                PlaatsParser.getVerblijfplaatsVoorOuder(ouder)
        );
    }

    public TableRow burgerlijkeStaat() {
        if (!isExtended()) {
            return TableRow.empty();
        }
        var vrouwelijkeOuder = (MoederOfOudsteOuderJSON) ouder;
        return new TableRow(
                "Burgerlijke staat",
                "-",
                "-",
                "-",
                "-",
                ofNullable(vrouwelijkeOuder.burgerlijkeStaat()).map(BurgerlijkeStaatJSONType::name).orElse("-")
        );
    }

    public TableRow datumHuidigHuwelijk() {
        if (!isExtended()) {
            return TableRow.empty();
        }
        var vrouwelijkeOuder = (MoederOfOudsteOuderJSON) ouder;
        return new TableRow(
                "Datum huidig huwelijk",
                "-",
                "-",
                "-",
                "-",
                ofNullable(vrouwelijkeOuder.huwelijk()).map(HuwelijkJSON::datum).map(TijdstipParser::parseLocalDate).orElse("-")
        );
    }

}
