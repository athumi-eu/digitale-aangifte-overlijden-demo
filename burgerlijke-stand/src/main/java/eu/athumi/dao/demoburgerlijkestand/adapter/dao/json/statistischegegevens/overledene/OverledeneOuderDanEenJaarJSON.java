package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;

import java.util.List;

public record OverledeneOuderDanEenJaarJSON(
        Geslacht geslacht,
        NationaliteitJSON nationaliteit,
        List<BurgerlijkeStaatJSON> burgerlijkeStaten,
        HuwelijkJSON huwelijk,
        GeboorteJSON geboorte,
        GemeenteEnLandJSON verblijfplaats
) implements OverledeneJSON {

    @Override
    public Geslacht geslacht() {
        return geslacht;
    }

}
