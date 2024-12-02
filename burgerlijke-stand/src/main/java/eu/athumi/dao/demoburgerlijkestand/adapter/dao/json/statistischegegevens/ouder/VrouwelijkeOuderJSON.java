package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;

import java.util.Set;

public record VrouwelijkeOuderJSON(
        NationaliteitJSON nationaliteit,
        GeboorteJSON geboorte,
        Geslacht geslacht,
        BurgerlijkeStaatJSONType burgerlijkeStaat,
        Set<NationaliteitJSON> oorspronkelijkeNationaliteit,
        AdresJSON verblijfplaats,
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
    public BurgerlijkeStaatJSONType burgerlijkeStaat() {
        return burgerlijkeStaat;
    }

    @Override
    public Set<NationaliteitJSON> oorspronkelijkeNationaliteit() {
        return oorspronkelijkeNationaliteit;
    }

}
