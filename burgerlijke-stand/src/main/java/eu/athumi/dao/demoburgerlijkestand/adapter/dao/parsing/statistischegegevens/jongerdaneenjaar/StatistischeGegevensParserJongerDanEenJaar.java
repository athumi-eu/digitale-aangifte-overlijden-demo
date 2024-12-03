package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensDepartementZorgJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OudersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;

public record StatistischeGegevensParserJongerDanEenJaar(StatistischeGegevensJSON statistischeGegevens) {

    private PersoonsgegevensDepartementZorgJSON getPersoonsGegevensVoorDepartementZorg() {
        return statistischeGegevens().persoonsgegevens().departementZorg();
    }

    private OverledeneJongerDanEenJaarJSON getOverledeneVoorDepartementZorg() {
        return (OverledeneJongerDanEenJaarJSON) getPersoonsGegevensVoorDepartementZorg().overledene();
    }

    private OudersJSON getOudersVoorDepartementZorg() {
        return getPersoonsGegevensVoorDepartementZorg().ouders();
    }

    public OverledenenParser overledene() {
        return new OverledenenParser(getOverledeneVoorDepartementZorg());
    }

    public OuderParser ouder1() {
        return new OuderParser(getOudersVoorDepartementZorg().moederOfOudsteOuder());
    }

    public OuderParser ouder2() {
        return new OuderParser(getOudersVoorDepartementZorg().vaderOfJongsteOuder());
    }

}
