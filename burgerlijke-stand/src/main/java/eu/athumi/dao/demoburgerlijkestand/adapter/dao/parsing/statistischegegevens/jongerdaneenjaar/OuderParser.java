package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.MoederOfOudsteOuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.BurgerlijkeStaatJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.MoederVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.PlaatsParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public record OuderParser(OuderJSON ouderDepartementZorg, MoederVaststellingJSON moederVaststelling,
                          OuderJSON ouderRR) {

    public boolean isExtended() {
        return ouderDepartementZorg instanceof MoederOfOudsteOuderJSON;
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                ofNullable(ouderRR).map(OuderJSON::geslacht).map(Geslacht::name).orElse("-"),
                ofNullable(moederVaststelling).map(MoederVaststellingJSON::geslacht).map(Geslacht::name).orElse("-"),
                "-",
                "-",
                ofNullable(ouderDepartementZorg).map(OuderJSON::geslacht).map(Geslacht::name).orElse("-")
        );
    }

    public TableRow geboorteDatum() {
        return new TableRow(
                "Geboortedatum",
                ofNullable(ouderRR).map(OuderJSON::geboorte).map(GeboorteJSON::datum).map(TijdstipParser::parseLocalDate).orElse("-"),
                "-",
                "-",
                "-",
                ofNullable(ouderDepartementZorg).map(OuderJSON::geboorte).map(GeboorteJSON::datum).map(TijdstipParser::parseLocalDate).orElse("-")
        );
    }

    public TableRow huidigeNationaliteit() {
        return new TableRow(
                "Huidige nationaliteit",
                ofNullable(ouderRR).map(OuderJSON::nationaliteit).map(NationaliteitJSON::naam).orElse("-"),
                "-",
                "-",
                "-",
                ofNullable(ouderDepartementZorg).map(OuderJSON::nationaliteit).map(NationaliteitJSON::naam).orElse("-")
        );
    }

    public TableRow oorspronkelijkeNationaliteit() {
        return new TableRow(
                "Oorspronkelijke nationaliteit(en)",
                "-",
                "-",
                "-",
                "-",
                ofNullable(ouderDepartementZorg.oorspronkelijkeNationaliteit()).map(nationaliteiten ->
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
                PlaatsParser.getVerblijfplaatsVoorOuder(ouderRR),
                PlaatsParser.getVerblijfplaatsVoorOuder(moederVaststelling),
                "-",
                "-",
                PlaatsParser.getVerblijfplaatsVoorOuder(ouderDepartementZorg)
        );
    }

    public TableRow burgerlijkeStaat() {
        if (!isExtended()) {
            return TableRow.empty();
        }
        var vrouwelijkeOuderRR = ouderRR instanceof MoederOfOudsteOuderJSON test? test: null;
        var vrouwelijkeOuderDepZorg = (MoederOfOudsteOuderJSON) ouderDepartementZorg;
        return new TableRow(
                "Burgerlijke staat",
                ofNullable(vrouwelijkeOuderRR).map(MoederOfOudsteOuderJSON::burgerlijkeStaten).map(staten -> staten.stream().map(BurgerlijkeStaatJSON::type).map(Enum::name).collect(Collectors.joining(", "))).orElse("-"),
                "-",
                "-",
                "-",
                ofNullable(vrouwelijkeOuderDepZorg).map(MoederOfOudsteOuderJSON::burgerlijkeStaten).map(staten -> staten.stream().map(BurgerlijkeStaatJSON::type).map(Enum::name).collect(Collectors.joining(", "))).orElse("-")
        );
    }

    public TableRow datumHuidigHuwelijk() {
        if (!isExtended()) {
            return TableRow.empty();
        }
        var vrouwelijkeOuderRR = ouderRR instanceof MoederOfOudsteOuderJSON test? test: null;
        var vrouwelijkeOuderDepZorg = (MoederOfOudsteOuderJSON) ouderDepartementZorg;
        return new TableRow(
                "Datum huidig huwelijk",
                ofNullable(vrouwelijkeOuderRR).map(MoederOfOudsteOuderJSON::burgerlijkeStaten).map(staten -> staten.stream().map(BurgerlijkeStaatJSON::huwelijksDatum).map(this::parseLocalDate).collect(Collectors.joining(", "))).orElse("-"),
                "-",
                "-",
                "-",
                ofNullable(vrouwelijkeOuderDepZorg).map(MoederOfOudsteOuderJSON::burgerlijkeStaten).map(staten -> staten.stream().map(BurgerlijkeStaatJSON::huwelijksDatum).map(this::parseLocalDate).collect(Collectors.joining(", "))).orElse("-")
        );
    }

    private String parseLocalDate(String date) {
        return ofNullable(date)
                .map(item -> {
                    if (item.length() != 10) {
                        return "-";
                    }
                    return TijdstipParser.parseLocalDate(LocalDate.of(Integer.parseInt(item.substring(0, 4)), Integer.parseInt(item.substring(5, 7)), Integer.parseInt(item.substring(8, 10))));
                }).orElse("");

    }

}
