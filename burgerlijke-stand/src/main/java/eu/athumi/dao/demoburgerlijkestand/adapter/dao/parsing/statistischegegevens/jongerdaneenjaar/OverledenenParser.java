package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.VerdelingVolgensGeslachtJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.PlaatsTypeJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalDate;
import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalTime;
import static java.util.Optional.ofNullable;

public record OverledenenParser(OverledeneJongerDanEenJaarJSON overledene) {

    public GeboorteJongerDanEenJaarJSON geboorte() {
        return (GeboorteJongerDanEenJaarJSON) overledene.geboorte();
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                "-",
                "-",
                "-",
                "-",
                ofNullable(overledene.geslacht()).map(Geslacht::name).orElse("-")
        );
    }

    public TableRow tijdstipOverlijden() {
        return new TableRow(
                "Tijdstip overlijden",
                "-",
                "-",
                "-",
                "-",
                // TODO DAO-136: Mappen als we de overlijdensgegevens hebben
                "-"
        );
    }

    public TableRow gemeenteOverlijden() {
        return new TableRow(
                "Gemeente van overlijden",
                "-",
                "-",
                "-",
                "-",
                // TODO DAO-136: Mappen als we de overlijdensgegevens hebben
                "-"
        );
    }

    public TableRow plaatsOverlijden() {
        return new TableRow(
                "Plaats van overlijden",
                "-",
                "-",
                "-",
                "-",
                // TODO DAO-136: Mappen als we de overlijdensgegevens hebben
                "-"
        );
    }

    public TableRow geboorteDatum() {
        return new TableRow(
                "Datum geboorte",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte()).map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-")
        );
    }

    public TableRow geboorteTijdstip() {
        return new TableRow(
                "Tijdstip geboorte",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte()).map(geboorte -> parseLocalTime(geboorte.tijdstip())).orElse("-")
        );
    }

    public TableRow gemeenteLandGeboorte() {
        return new TableRow(
                "Gemeente/ land van geboorte",
                "-",
                "-",
                "-",
                "-",
                // Vragen
                "Nog te vragen"
        );
    }

    public TableRow plaatsGeboorte() {
        return new TableRow(
                "Plaats van geboorte",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().plaats()).map(PlaatsTypeJSON::name).orElse("-")
        );
    }

    public TableRow levendOfDoodGeboren() {
        return new TableRow(
                "Levend geboren of doodgeboren",
                "-",
                "-",
                "-",
                "-",
                //TODO DAO-136: van waar komt dit
                "Nog op te zoeken"
        );
    }

    public TableRow meervoudigeZwangerschap() {
        return new TableRow(
                "Meervoudige zwangerschap",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().meervoudigeZwangerschap()).map(meervoudigeZwangerschap -> meervoudigeZwangerschap ? "Ja" : "Neen").orElse("-")
        );
    }

    public TableRow totaal() {
        return new TableRow(
                "Totaal aantal geboren",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().totaalAantalGeboren()).map(Object::toString).orElse("-")
        );
    }

    public TableRow rangNummer() {
        return new TableRow(
                "Rangnummer",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().rangnummer()).map(Object::toString).orElse("-")
        );
    }

    public TableRow levendMannelijk() {
        return new TableRow(
                "Aantal levend geboren mannelijk",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().aantalLevendGeboren()).map(VerdelingVolgensGeslachtJSON::mannelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow levendVrouwelijk() {
        return new TableRow(
                "Aantal levend geboren vrouwelijk",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().aantalLevendGeboren()).map(VerdelingVolgensGeslachtJSON::vrouwelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow levendOnbepaald() {
        return new TableRow(
                "Aantal levend geboren onbepaald",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().aantalLevendGeboren()).map(VerdelingVolgensGeslachtJSON::onbepaald).map(Object::toString).orElse("-")
        );
    }

    public TableRow doodMannelijk() {
        return new TableRow(
                "Aantal dood geboren mannelijk",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().aantalDoodGeboren()).map(VerdelingVolgensGeslachtJSON::mannelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow doodVrouwelijk() {
        return new TableRow(
                "Aantal dood geboren vrouwelijk",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().aantalDoodGeboren()).map(VerdelingVolgensGeslachtJSON::vrouwelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow doodOnbepaald() {
        return new TableRow(
                "Aantal dood geboren onbepaald",
                "-",
                "-",
                "-",
                "-",
                ofNullable(geboorte().aantalDoodGeboren()).map(VerdelingVolgensGeslachtJSON::onbepaald).map(Object::toString).orElse("-")
        );
    }
}
