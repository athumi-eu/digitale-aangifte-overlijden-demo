package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische.*;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkOverledeneJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.Plaats;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.PlaatsTypeJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.BurgerlijkeStaatJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneOuderDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.*;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister.OverledeneRijksregisterJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister.PersoonsgegevensRijksregisterJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.PersoonsgegevensVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalDate;
import static java.util.Optional.ofNullable;

public record StatistischeGegevensParserOuderDanEenJaar(StatistischeGegevensJSON statistischeGegevens) {
    static final String DASH = "-";

    private OverledeneOuderDanEenJaarJSON getOverledeneVoorDepartementZorg() {
        return (OverledeneOuderDanEenJaarJSON) statistischeGegevens().persoonsgegevens().departementZorg().overledene();
    }

    private Optional<SEG> seg() {
        return Optional.ofNullable(statistischeGegevens).map(StatistischeGegevensJSON::socioeconomischegegevens);
    }

    private Optional<OverledeneOuderDanEenJaarJSON> getOverledeneVoorVaststelling() {
        return Optional.ofNullable(statistischeGegevens().persoonsgegevens().vaststelling())
                .map(PersoonsgegevensVaststellingJSON::overledene)
                .map(v -> (OverledeneOuderDanEenJaarJSON) v);
    }

    private Optional<OverledeneRijksregisterJSON> getOverledeneVoorRijksregister() {
        return Optional.ofNullable(statistischeGegevens().persoonsgegevens().rijksregister())
                .map(PersoonsgegevensRijksregisterJSON::overledene);
    }

    private HuwelijkOverledeneJSON getHuwelijkOverledene() {
        return (HuwelijkOverledeneJSON) getOverledeneVoorDepartementZorg().huwelijk();
    }

    private HuwelijkOverledeneJSON getHuwelijkOverledeneVaststelling() {
        return (HuwelijkOverledeneJSON) getOverledeneVoorVaststelling()
                .map(OverledeneOuderDanEenJaarJSON::huwelijk)
                .orElse(null);
    }

    public Optional<OverlijdenStatistischJSON> overlijdenVaststelling() {
        return overlijdensgegevens().map(OverlijdensgegevensJSON::overlijdensgegevensVaststelling).map(OverlijdensgegevensVaststellingJSON::overlijden);
    }

    public Optional<OverlijdenStatistischJSON> overlijdenDepartementZorg() {
        return overlijdensgegevens().map(OverlijdensgegevensJSON::overlijdensgegevensDepartementZorg).map(OverlijdensgegevensDepartementZorgJSON::overlijden);
    }

    public Optional<OverlijdensgegevensJSON> overlijdensgegevens() {
        return Optional.ofNullable(statistischeGegevens.overlijdensgegevens());
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::geslacht).map(Geslacht::name).orElse("-"),
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::geslacht).map(Geslacht::name).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().geslacht()).map(Geslacht::name).orElse("-")
        );
    }

    public TableRow tijdstipOverlijden() {
        return new TableRow(
                "Tijdstip overlijden",
                "-",
                overlijdenVaststelling().map(OverlijdenStatistischJSON::tijdstip).map(TijdstipParser::parseLocalDateTime).or(() -> overlijdenVaststelling().map(OverlijdenStatistischJSON::beschrijvingTijdstip)).orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().map(OverlijdenStatistischJSON::tijdstip).map(TijdstipParser::parseLocalDateTime).or(() -> overlijdenVaststelling().map(OverlijdenStatistischJSON::beschrijvingTijdstip)).orElse("-")
        );
    }

    public TableRow gemeenteOverlijden() {
        return new TableRow(
                "Gemeente van overlijden",
                "-",
                overlijdenVaststelling().map(OverlijdenStatistischJSON::adres).map(AdresStatistischJSON::gemeente).map(GemeenteJSON::niscode).orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().map(OverlijdenStatistischJSON::adres).map(AdresStatistischJSON::gemeente).map(GemeenteJSON::niscode).orElse("-")
        );
    }

    public TableRow plaatsOverlijden() {
        return new TableRow(
                "Plaats van overlijden",
                "-",
                overlijdenVaststelling().flatMap(this::parsePlaats).orElse("-"),
                "-",
                "-",
                overlijdenDepartementZorg().flatMap(this::parsePlaats).orElse("-")
        );
    }

    public TableRow geboorteDatum() {
        return new TableRow(
                "Geboortedatum",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::geboortedatum).map(TijdstipParser::parseLocalDate).orElse("-"),
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::geboorte).map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().geboorte()).map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-")
        );
    }

    public TableRow nationaliteit() {
        return new TableRow(
                "Nationaliteit",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::nationaliteit).map(NationaliteitJSON::naam).orElse("-"),
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::nationaliteit).map(NationaliteitJSON::naam).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().nationaliteit()).map(NationaliteitJSON::naam).orElse("-")
        );
    }

    public TableRow verblijfplaats() {
        return new TableRow(
                "Verblijfplaats",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::verblijfsAdres).map(AdresJSON::gemeente).map(GemeenteJSON::niscode).orElse("-"),
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::verblijfplaats).map(AdresJSON::gemeente).map(GemeenteJSON::niscode).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().verblijfplaats()).map(AdresJSON::gemeente).map(GemeenteJSON::niscode).orElse("-")
        );
    }

    public TableRow burgerlijkeStaat() {
        return new TableRow(
                "Burgerlijke staat",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::burgerlijkeStaten).map(t -> t.stream().map(BurgerlijkeStaatJSON::type).map(Enum::name).collect(Collectors.joining(","))).orElse("-"),
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::burgerlijkeStaten).map(t -> t.stream().map(BurgerlijkeStaatJSON::type).map(Enum::name).collect(Collectors.joining(","))).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().burgerlijkeStaten()).map(t -> t.stream().map(BurgerlijkeStaatJSON::type).map(Enum::name).collect(Collectors.joining(","))).orElse("-")
        );
    }

    public TableRow huwelijksDatum() {
        return new TableRow(
                "Datum huidig huwelijk",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::burgerlijkeStaten).map(t -> t.stream().map(BurgerlijkeStaatJSON::huwelijksDatum).collect(Collectors.joining(","))).orElse("-"),
                ofNullable(getHuwelijkOverledeneVaststelling()).map(huwelijk -> parseLocalDate(huwelijk.datum())).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().burgerlijkeStaten()).map(t -> t.stream().map(BurgerlijkeStaatJSON::huwelijksDatum).collect(Collectors.joining(","))).orElse("-")
        );
    }

    public TableRow geboorteDatumPartner() {
        return new TableRow(
                "Geboortedatum overlevende partner",
                getOverledeneVoorRijksregister().map(OverledeneRijksregisterJSON::burgerlijkeStaten).map(t -> t.stream().map(BurgerlijkeStaatJSON::geboortedatumPartner).map(TijdstipParser::parseLocalDate).collect(Collectors.joining(","))).orElse("-"),
                ofNullable(getHuwelijkOverledeneVaststelling()).map(huwelijk -> parseLocalDate(huwelijk.geboorteDatumOverlevendePartner())).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().burgerlijkeStaten()).map(t -> t.stream().map(BurgerlijkeStaatJSON::geboortedatumPartner).map(TijdstipParser::parseLocalDate).collect(Collectors.joining(","))).orElse("-"));

    }

    private String parseOpleiding(Opleiding seg) {
        if (seg != null) {
            if (seg.opleidingAndere() != null && !seg.opleidingAndere().isBlank()) {
                return seg.opleidingAndere();
            } else {
                var extraInfo = Optional.ofNullable(seg.onderwijsType()).map(OnderwijsType::getLabel).orElse("");
                var type = Optional.ofNullable(seg.type()).map(OpleidingType::getLabel).orElse("");
                var joinedInfo =  type + " " + extraInfo;
                return joinedInfo.isBlank() ? DASH : joinedInfo;
            }
        }
        return DASH;
    }

    public TableRow opleiding() {
        return new TableRow(
                "Hoogst voltooide opleiding",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH)
        );
    }

    private String parseBeroepstoestand(Beroepstoestand seg) {
        if (seg != null) {
            if (seg.beroepstoestandAndere() != null && !seg.beroepstoestandAndere().isBlank()) {
                return seg.beroepstoestandAndere();
            } else {
                return  Optional.ofNullable(seg.type()).map(String::valueOf).orElse(DASH);
            }
        }
        return DASH;
    }

    public TableRow beroepstoestand() {
        return new TableRow(
                "Huidige beroepstoestand",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH)
        );
    }

    private String parseSocialeStaat(SocialeStaat seg) {
        if (seg != null) {
            if (seg.socialeStaatAndere() != null && !seg.socialeStaatAndere().isBlank()) {
                return seg.socialeStaatAndere();
            } else {
                return  Optional.ofNullable(seg.type()).map(String::valueOf).orElse(DASH);
            }
        }
        return DASH;
    }

    public TableRow socialestaat() {
        return new TableRow(
                "Sociale staat huidig beroep",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH)
        );
    }

    public TableRow beroep1() {
        return new TableRow(
                "Huidig beroep (laatst uitgeoefend)",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(Predicate.not(List::isEmpty)).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(Predicate.not(List::isEmpty)).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(Predicate.not(List::isEmpty)).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH)
        );
    }

    public TableRow beroep2() {
        return new TableRow(
                "Vorig beroep 1",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(l -> l.size() > 1).map(l -> l.get(1)).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(l -> l.size() > 1).map(l -> l.get(1)).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(l -> l.size() > 1).map(l -> l.get(1)).map(b -> b.omschrijving()).orElse(DASH)
        );
    }

    public TableRow beroep3() {
        return new TableRow(
                "Vorig beroep 2",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(l -> l.size() > 2).map(l -> l.get(2)).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(l -> l.size() > 2).map(l -> l.get(2)).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).filter(l -> l.size() > 2).map(l -> l.get(2)).map(b -> b.omschrijving()).orElse(DASH)
        );
    }

    private String parseWoonsituatie(Woonsituatie seg) {
        if (seg != null) {
            if (seg.woonsituatieAndere() != null && !seg.woonsituatieAndere().isBlank()) {
                return seg.woonsituatieAndere();
            } else {
                return Optional.ofNullable(seg.type()).map(String::valueOf).orElse(DASH);
            }
        }
        return DASH;
    }

    public TableRow woonsituatie() {
        return new TableRow(
                "woonsituatie",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.overledene()).map(o -> o.woonsituatie()).map(this::parseWoonsituatie).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.overledene()).map(o -> o.woonsituatie()).map(this::parseWoonsituatie).orElse(DASH),
                seg().map(s -> s.departementZorg()).map(l -> l.overledene()).map(o -> o.woonsituatie()).map(this::parseWoonsituatie).orElse(DASH)
        );
    }


    private Optional<String> parsePlaats(Plaats plaats) {
        if (plaats.plaats() == PlaatsTypeJSON.ANDERE) {
            return Optional.ofNullable(plaats.plaatsBeschrijving()).map(beschrijving -> String.format("ANDERE: %s", beschrijving));
        }
        return Optional.ofNullable(plaats.plaats()).map(Enum::name);
    }
}
