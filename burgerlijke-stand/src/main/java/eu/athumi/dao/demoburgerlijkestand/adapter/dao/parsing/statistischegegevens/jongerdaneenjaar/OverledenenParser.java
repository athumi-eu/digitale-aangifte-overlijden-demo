package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.MeervoudigeZwangerschapJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.VerdelingVolgensGeslachtJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.PlaatsTypeJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.util.Objects;
import java.util.Optional;

import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalDate;
import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalTime;
import static java.util.Optional.ofNullable;

public record OverledenenParser(
        StatistischeGegevensJSON statistischeGegevensJSON,
        OverledeneJongerDanEenJaarJSON departementZorg,
        Optional<OverledeneJongerDanEenJaarJSON> vaststelling) {


    public Optional<MeervoudigeZwangerschapJSON> meervoudigeZwangerschapData() {
        return Optional.ofNullable(statistischeGegevensJSON.meervoudigeZwangerschap());
    }
    public GeboorteJongerDanEenJaarJSON geboorte() {
        return (GeboorteJongerDanEenJaarJSON) departementZorg.geboorte();
    }

    public Optional<GeboorteJongerDanEenJaarJSON> geboorteVaststelling() {
        return vaststelling.map(OverledeneJongerDanEenJaarJSON::geboorte).map(o -> (GeboorteJongerDanEenJaarJSON) o);
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                "-",
                vaststelling.map(OverledeneJongerDanEenJaarJSON::geslacht).map(Geslacht::name).orElse("-"),
                "-",
                "-",
                ofNullable(departementZorg.geslacht()).map(Geslacht::name).orElse("-")
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
                geboorteVaststelling().map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-"),
                "-",
                "-",
                ofNullable(geboorte()).map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-")
        );
    }

    public TableRow geboorteTijdstip() {
        return new TableRow(
                "Tijdstip geboorte",
                "-",
                geboorteVaststelling().map(geboorte -> parseLocalTime(geboorte.tijdstip())).orElse("-"),
                "-",
                "-",
                ofNullable(geboorte()).map(geboorte -> parseLocalTime(geboorte.tijdstip())).orElse("-")
        );
    }

    public TableRow gemeenteLandGeboorte() {
        return new TableRow(
                "Gemeente/ land van geboorte",
                "-",
                parseVerblijfplaats(geboorteVaststelling().map(GeboorteJongerDanEenJaarJSON::adres).orElse(null)),
                "-",
                "-",
                parseVerblijfplaats(geboorte().adres())
        );
    }

    private String parseVerblijfplaats(AdresJSON adres) {
        var gemeente = ofNullable(adres).map(AdresJSON::gemeente).orElse(null);
        var land = ofNullable(adres).map(AdresJSON::land).orElse(null);
        return Objects.isNull(gemeente) ? (Objects.isNull(land) ? "-" : land.naam()) : gemeente.niscode();
    }

    public TableRow plaatsGeboorte() {
        return new TableRow(
                "Plaats van geboorte",
                "-",
                geboorteVaststelling().flatMap(this::parsePlaats).orElse("-"),
                "-",
                "-",
                ofNullable(geboorte()).flatMap(this::parsePlaats).orElse("-")
        );
    }

    private Optional<String> parsePlaats(GeboorteJongerDanEenJaarJSON v) {
        if (v.plaats() == PlaatsTypeJSON.ANDERE) {
            return Optional.ofNullable(v.plaatsBeschrijving()).map(beschrijving -> String.format("ANDERE: %s", beschrijving));
        }
        return Optional.ofNullable(v.plaats()).map(Enum::name);
    }

    public TableRow levendOfDoodGeboren() {
        return new TableRow(
                "Levend geboren of doodgeboren",
                "-",
                // TODO DAO-136: Mappen als we de overlijdensgegevens hebben
                "Nog te mappen",
                "-",
                "-",
                // TODO DAO-136: Mappen als we de overlijdensgegevens hebben
                "Nog te mappen"
        );
    }

    public TableRow meervoudigeZwangerschap() {
        return new TableRow(
                "Meervoudige zwangerschap",
                "-",
                geboorteVaststelling().map(GeboorteJongerDanEenJaarJSON::meervoudigeZwangerschap).map(meervoudigeZwangerschap -> meervoudigeZwangerschap ? "Ja" : "Neen").orElse("-"),
                "-",
                "-",
                ofNullable(geboorte().meervoudigeZwangerschap()).map(meervoudigeZwangerschap -> meervoudigeZwangerschap ? "Ja" : "Neen").orElse("-")
        );
    }

    public TableRow totaal() {
        return new TableRow(
                "Totaal aantal geboren",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::totaalAantalKinderen).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::totaalAantalKinderen).map(Object::toString).orElse("-")
        );
    }

    public TableRow rangNummer() {
        return new TableRow(
                "Rangnummer",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::rangOverledene).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::rangOverledene).map(Object::toString).orElse("-")
        );
    }

    public TableRow levendMannelijk() {
        return new TableRow(
                "Aantal levend geboren mannelijk",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalLevendGeboren).map(VerdelingVolgensGeslachtJSON::mannelijk).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalLevendGeboren).map(VerdelingVolgensGeslachtJSON::mannelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow levendVrouwelijk() {
        return new TableRow(
                "Aantal levend geboren vrouwelijk",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalLevendGeboren).map(VerdelingVolgensGeslachtJSON::vrouwelijk).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalLevendGeboren).map(VerdelingVolgensGeslachtJSON::vrouwelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow levendOnbepaald() {
        return new TableRow(
                "Aantal levend geboren onbepaald",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalLevendGeboren).map(VerdelingVolgensGeslachtJSON::onbepaald).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalLevendGeboren).map(VerdelingVolgensGeslachtJSON::onbepaald).map(Object::toString).orElse("-")
        );
    }

    public TableRow doodMannelijk() {
        return new TableRow(
                "Aantal dood geboren mannelijk",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalDoodGeboren).map(VerdelingVolgensGeslachtJSON::mannelijk).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalDoodGeboren).map(VerdelingVolgensGeslachtJSON::mannelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow doodVrouwelijk() {
        return new TableRow(
                "Aantal dood geboren vrouwelijk",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalDoodGeboren).map(VerdelingVolgensGeslachtJSON::vrouwelijk).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalDoodGeboren).map(VerdelingVolgensGeslachtJSON::vrouwelijk).map(Object::toString).orElse("-")
        );
    }

    public TableRow doodOnbepaald() {
        return new TableRow(
                "Aantal dood geboren onbepaald",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalDoodGeboren).map(VerdelingVolgensGeslachtJSON::onbepaald).map(Object::toString).orElse("-"),
                "-",
                "-",
                meervoudigeZwangerschapData().map(MeervoudigeZwangerschapJSON::aantalDoodGeboren).map(VerdelingVolgensGeslachtJSON::onbepaald).map(Object::toString).orElse("-")
        );
    }
}
