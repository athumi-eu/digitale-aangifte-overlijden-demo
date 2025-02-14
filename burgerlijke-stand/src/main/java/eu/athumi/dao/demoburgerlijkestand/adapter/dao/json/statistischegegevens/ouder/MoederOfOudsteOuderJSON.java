package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.BurgerlijkeStaatJSON;

import java.util.List;
import java.util.Set;

public record MoederOfOudsteOuderJSON(
        NationaliteitJSON nationaliteit,
        GeboorteJSON geboorte,
        Geslacht geslacht,
        List<BurgerlijkeStaatJSON> burgerlijkeStaten,
        Set<NationaliteitJSON> oorspronkelijkeNationaliteit,
        GemeenteEnLandJSON verblijfplaats,
        HuwelijkJSON huwelijk
) implements OuderJSON {

    @Override
    public NationaliteitJSON nationaliteit() {
        return nationaliteit;
    }

    @Override
    public GeboorteJSON geboorte() {
        return geboorte;
    }

    @Override
    public Geslacht geslacht() {
        return geslacht;
    }

    @Override
    public List<BurgerlijkeStaatJSON> burgerlijkeStaten() {
        return burgerlijkeStaten;
    }

    @Override
    public Set<NationaliteitJSON> oorspronkelijkeNationaliteit() {
        return oorspronkelijkeNationaliteit;
    }

}
