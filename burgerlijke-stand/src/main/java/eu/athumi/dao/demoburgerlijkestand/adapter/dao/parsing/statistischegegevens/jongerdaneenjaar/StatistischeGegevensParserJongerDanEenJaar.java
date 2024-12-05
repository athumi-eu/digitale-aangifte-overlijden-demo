package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensDepartementZorgJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OudersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummerPersoonJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummersJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public record StatistischeGegevensParserJongerDanEenJaar(StatistischeGegevensJSON statistischeGegevens) {

    private PersoonsgegevensDepartementZorgJSON getPersoonsGegevensVoorDepartementZorg() {
        return statistischeGegevens().persoonsgegevens().departementZorg();
    }

    private OverledeneJongerDanEenJaarJSON getOverledeneVoorDepartementZorg() {
        return (OverledeneJongerDanEenJaarJSON) getPersoonsGegevensVoorDepartementZorg().overledene();
    }

    private Optional<PersoonsgegevensVaststellingJSON> getPersoonsGegevensVoorVaststelling() {
        return Optional.ofNullable(statistischeGegevens().persoonsgegevens().vaststelling());
    }

    private Optional<OverledeneJongerDanEenJaarJSON> getOverledeneVoorVaststelling() {
        return getPersoonsGegevensVoorVaststelling()
                .map(PersoonsgegevensVaststellingJSON::overledene)
                .map(o -> (OverledeneJongerDanEenJaarJSON) o);
    }

    private OudersJSON getOudersVoorDepartementZorg() {
        return getPersoonsGegevensVoorDepartementZorg().ouders();
    }

    private OudersJSON getOudersVoorVaststelling() {
        return getPersoonsGegevensVoorVaststelling().map(PersoonsgegevensVaststellingJSON::ouders).orElse(null);
    }

    public OverledenenParser overledene() {
        return new OverledenenParser(statistischeGegevens(), getOverledeneVoorDepartementZorg(), getOverledeneVoorVaststelling());
    }

    public OuderParser ouder1() {
        return new OuderParser(getOudersVoorDepartementZorg().moederOfOudsteOuder(), ofNullable(getOudersVoorVaststelling()).map(OudersJSON::moederOfOudsteOuder).orElse(null));
    }

    public OuderParser ouder2() {
        return new OuderParser(getOudersVoorDepartementZorg().vaderOfJongsteOuder(), ofNullable(getOudersVoorVaststelling()).map(OudersJSON::vaderOfJongsteOuder).orElse(null));
    }

    public TableRow rrnOverledene() {
        return new TableRow(
                "RRN overledenen",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerRijksregister).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerVaststelling).orElse("-"),
                "-",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerLokaalBestuur).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerDepartementZorg).orElse("-")
        );
    }

    public TableRow rrnMoeder() {
        return new TableRow(
                "RRN moeder",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerRijksregister).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerVaststelling).orElse("-"),
                "-",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerLokaalBestuur).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerDepartementZorg).orElse("-")
        );
    }

    public TableRow rrnVaderOfMoeder() {
        return new TableRow(
                "RRN vader/meemoeder",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerRijksregister).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerVaststelling).orElse("-"),
                "-",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerLokaalBestuur).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerDepartementZorg).orElse("-")
        );
    }

}
