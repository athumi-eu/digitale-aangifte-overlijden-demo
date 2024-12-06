package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensDepartementZorgJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.MoederVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.PersoonsgegevensVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.MoederOfOudsteOuderJSON;
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

    private MoederVaststellingJSON getMoederVoorVaststelling() {
        return getPersoonsGegevensVoorVaststelling().map(PersoonsgegevensVaststellingJSON::moeder).orElse(null);
    }

    public OverledenenParser overledene() {
        return new OverledenenParser(statistischeGegevens(), getOverledeneVoorDepartementZorg(), getOverledeneVoorVaststelling());
    }

    public OuderParser ouder1() {
        return new OuderParser(getOudersVoorDepartementZorg().moederOfOudsteOuder(), getMoederVoorVaststelling());
    }

    public OuderParser ouder2() {
        return new OuderParser(getOudersVoorDepartementZorg().vaderOfJongsteOuder(), null);
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
