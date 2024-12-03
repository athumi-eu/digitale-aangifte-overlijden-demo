package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.VrouwelijkeOuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public record OuderParser(OuderJSON ouder) {

    public boolean isExtended() {
        return ouder instanceof VrouwelijkeOuderJSON;
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                "-",
                "-",
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
        var vrouwelijkeOuder = (VrouwelijkeOuderJSON) ouder;
        var gemeente = ofNullable(vrouwelijkeOuder.verblijfplaats()).map(AdresJSON::gemeente).orElse(null);
        var land = ofNullable(vrouwelijkeOuder.verblijfplaats()).map(AdresJSON::land).orElse(null);
        return new TableRow(
                "Verblijfplaats",
                "-",
                "-",
                "-",
                "-",
                Objects.isNull(gemeente) ? (Objects.isNull(land) ? "-" : land.naam()) : gemeente.naam()
        );
    }

    public TableRow burgerlijkeStaat() {
        if (!isExtended()) {
            return TableRow.empty();
        }
        var vrouwelijkeOuder = (VrouwelijkeOuderJSON) ouder;
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
        var vrouwelijkeOuder = (VrouwelijkeOuderJSON) ouder;
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
