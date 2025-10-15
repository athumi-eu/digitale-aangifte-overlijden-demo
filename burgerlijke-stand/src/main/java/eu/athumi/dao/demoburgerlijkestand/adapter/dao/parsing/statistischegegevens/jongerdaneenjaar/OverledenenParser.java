package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Gemeente;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.MeervoudigeZwangerschapJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.VerdelingVolgensGeslachtJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.Plaats;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.*;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister.OverledeneRijksregisterJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister.PersoonsgegevensRijksregisterJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.util.Optional;

import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.*;
import static java.util.Optional.ofNullable;

public record OverledenenParser(
        StatistischeGegevensJSON statistischeGegevensJSON,
        OverledeneJongerDanEenJaarJSON departementZorg,
        Optional<OverledeneJongerDanEenJaarJSON> vaststelling) {


    public Optional<MeervoudigeZwangerschapJSON> meervoudigeZwangerschapData() {
        return overlijdensgegevens().map(OverlijdensgegevensJSON::overlijdensgegevensVaststelling).map(OverlijdensgegevensVaststellingJSON::meervoudigeZwangerschap);
    }

    public Optional<OverlijdenStatistischJSON> overlijdenVastelling() {
        return overlijdensgegevens().map(OverlijdensgegevensJSON::overlijdensgegevensVaststelling).map(OverlijdensgegevensVaststellingJSON::overlijden);
    }

    public Optional<OverlijdenStatistischJSON> overlijdenDepartementZorg() {
        return overlijdensgegevens().map(OverlijdensgegevensJSON::overlijdensgegevensDepartementZorg).map(OverlijdensgegevensDepartementZorgJSON::overlijden);
    }

    public Optional<OverlijdensgegevensJSON> overlijdensgegevens() {
        return Optional.ofNullable(statistischeGegevensJSON.overlijdensgegevens());
    }

    public Optional<OverledeneRijksregisterJSON> persoonRR() {
        return Optional.ofNullable(statistischeGegevensJSON.persoonsgegevens()).map(PersoonsgegevensJSON::rijksregister).map(PersoonsgegevensRijksregisterJSON::overledene);
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
                persoonRR().map(OverledeneRijksregisterJSON::geslacht).map(Geslacht::name).orElse("-"),
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
                overlijdenVastelling().map(OverlijdenStatistischJSON::tijdstip).map(TijdstipParser::parseLocalDateTime).or(() -> overlijdenVastelling().map(OverlijdenStatistischJSON::beschrijvingTijdstip)).orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().map(OverlijdenStatistischJSON::tijdstip).map(TijdstipParser::parseLocalDateTime).or(() -> overlijdenVastelling().map(OverlijdenStatistischJSON::beschrijvingTijdstip)).orElse("-")
        );
    }

    public TableRow gemeenteOverlijden() {
        return new TableRow(
                "Gemeente van overlijden",
                "-",
                overlijdenVastelling().map(OverlijdenStatistischJSON::adres).map(GemeenteEnLandJSON::gemeente).map(Gemeente::niscode).orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().map(OverlijdenStatistischJSON::adres).map(GemeenteEnLandJSON::gemeente).map(Gemeente::niscode).orElse("-")
        );
    }

    public TableRow plaatsOverlijden() {
        return new TableRow(
                "Plaats van overlijden",
                "-",
                overlijdenVastelling().flatMap(this::parsePlaats).orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().flatMap(this::parsePlaats).orElse("-")
        );
    }

    public TableRow geboorteDatum() {
        return new TableRow(
                "Datum geboorte",
                persoonRR().map(OverledeneRijksregisterJSON::geboortedatum).map(TijdstipParser::parseLocalDate).orElse("-"),
                geboorteVaststelling().map(geboorte -> parseDateString(geboorte.datum())).orElse("-"),
                "-",
                "-",
                ofNullable(geboorte()).map(geboorte -> parseDateString(geboorte.datum())).orElse("-")
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
                persoonRR().map(OverledeneRijksregisterJSON::geboorteAdres).map(GemeenteEnLand::toString).orElse("-"),
                geboorteVaststelling().map(GeboorteJongerDanEenJaarJSON::adres).map(GemeenteEnLand::toString).orElse("-"),
                "-",
                "-",
                Optional.ofNullable(geboorte()).map(GeboorteJongerDanEenJaarJSON::adres).map(GemeenteEnLand::toString).orElse("-")
        );
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

    private Optional<String> parsePlaats(Plaats plaats) {
        if (plaats.plaats() == PlaatsType.ANDERE) {
            return Optional.ofNullable(plaats.plaatsBeschrijving()).map(beschrijving -> String.format("ANDERE: %s", beschrijving));
        }
        return Optional.ofNullable(plaats.plaats()).map(Enum::name);
    }

    private Optional<String> parsePlaats(OverlijdenStatistischJSON plaats) {
        if (PlaatsType.ANDERE.equals(plaats.plaats())) {
            return Optional.ofNullable(plaats.plaatsBeschrijving()).map(beschrijving -> String.format("ANDERE: %s", beschrijving));
        }
        return Optional.ofNullable(plaats.plaats()).map(Enum::name);
    }

    public TableRow levendOfDoodGeboren() {
        return new TableRow(
                "Levend geboren of doodgeboren",
                "-",
                overlijdenVastelling().map(OverlijdenStatistischJSON::doodGeboren).map(d -> d ? "Doodgeboren" : "Levend geboren").orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().map(OverlijdenStatistischJSON::doodGeboren).map(d -> d ? "Doodgeboren" : "Levend geboren").orElse("-")
        );
    }

    public TableRow meervoudigeZwangerschap() {
        return new TableRow(
                "Meervoudige zwangerschap",
                "-",
                geboorteVaststelling().map(GeboorteJongerDanEenJaarJSON::meervoudigeZwangerschap).map(meervoudigeZwangerschap -> meervoudigeZwangerschap ? "ja" : "neen").orElse("-"),
                "-",
                "-",
                ofNullable(geboorte().meervoudigeZwangerschap()).map(meervoudigeZwangerschap -> meervoudigeZwangerschap ? "ja" : "neen").orElse("-")
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
