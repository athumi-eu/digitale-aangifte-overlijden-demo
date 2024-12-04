package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensDepartementZorgJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OudersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import static java.util.Optional.ofNullable;

public record StatistischeGegevensParserJongerDanEenJaar(StatistischeGegevensJSON statistischeGegevens) {

    private PersoonsgegevensDepartementZorgJSON getPersoonsGegevensVoorDepartementZorg() {
        return statistischeGegevens().persoonsgegevens().departementZorg();
    }

    private OverledeneJongerDanEenJaarJSON getOverledeneVoorDepartementZorg() {
        return (OverledeneJongerDanEenJaarJSON) getPersoonsGegevensVoorDepartementZorg().overledene();
    }

    private PersoonsgegevensVaststellingJSON getPersoonsGegevensVoorVaststelling() {
        return statistischeGegevens().persoonsgegevens().vaststelling();
    }

    private OverledeneJongerDanEenJaarJSON getOverledeneVoorVaststelling() {
        return (OverledeneJongerDanEenJaarJSON) getPersoonsGegevensVoorVaststelling().overledene();
    }

    private OudersJSON getOudersVoorDepartementZorg() {
        return getPersoonsGegevensVoorDepartementZorg().ouders();
    }

    private OudersJSON getOudersVoorVaststelling() {
        return getPersoonsGegevensVoorVaststelling().ouders();
    }

    public OverledenenParser overledene() {
        return new OverledenenParser(getOverledeneVoorDepartementZorg(), getOverledeneVoorVaststelling());
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
                "-",
                "-",
                "-",
                "-",
                "-"
        );
    }

    public TableRow rrnMoeder() {
        return new TableRow(
                "RRN moeder",
                "-",
                "-",
                "-",
                "-",
                "-"
        );
    }

    public TableRow rrnVaderOfMoeder() {
        return new TableRow(
                "RRN vader/meemoeder",
                "-",
                "-",
                "-",
                "-",
                "-"
        );
    }

}
