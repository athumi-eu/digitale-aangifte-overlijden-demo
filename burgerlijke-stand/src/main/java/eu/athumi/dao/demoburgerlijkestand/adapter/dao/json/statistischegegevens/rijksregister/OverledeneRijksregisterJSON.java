package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.BurgerlijkeStaatJSON;

import java.time.LocalDate;
import java.util.List;

public record OverledeneRijksregisterJSON(
        Geslacht geslacht,
        LocalDate geboortedatum,
        GemeenteEnLand geboorteAdres,
        AdresJSON verblijfsAdres,
        NationaliteitJSON nationaliteit,
        List<BurgerlijkeStaatJSON> burgerlijkeStaten
) {
}
