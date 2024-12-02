package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.PlaatsTypeJSON;

import java.time.LocalDate;
import java.time.LocalTime;

public record GeboorteJongerDanEenJaarJSON(
        LocalDate datum,
        LocalTime tijdstip,
        PlaatsTypeJSON plaats,
        AdresJSON adres,
        Boolean meervoudigeZwangerschap,
        Integer totaalAantalGeboren,
        Integer rangnummer,
        VerdelingVolgensGeslachtJSON aantalLevendGeboren,
        VerdelingVolgensGeslachtJSON aantalDoodGeboren
) implements GeboorteJSON {

    @Override
    public LocalDate datum() {
        return datum;
    }

}
