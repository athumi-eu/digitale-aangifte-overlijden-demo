package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;

public record OverledeneJongerDanEenJaarJSON(
        Geslacht geslacht,
        GeboorteJSON geboorte
) implements OverledeneJSON {

    @Override
    public Geslacht geslacht() {
        return geslacht;
    }
}
