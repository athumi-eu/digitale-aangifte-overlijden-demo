package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;

import java.util.Set;

public record MannelijkeOuderJSON(
        NationaliteitJSON nationaliteit,
        GeboorteJSON geboorte,
        Geslacht geslacht,
        Set<NationaliteitJSON> oorspronkelijkeNationaliteit
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
    public Set<NationaliteitJSON> oorspronkelijkeNationaliteit() {
        return oorspronkelijkeNationaliteit;
    }
}
