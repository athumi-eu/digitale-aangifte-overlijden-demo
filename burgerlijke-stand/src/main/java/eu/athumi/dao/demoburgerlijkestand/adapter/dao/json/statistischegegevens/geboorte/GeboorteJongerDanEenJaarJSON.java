package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.Plaats;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.PlaatsType;

import java.time.LocalDate;
import java.time.LocalTime;

public record GeboorteJongerDanEenJaarJSON(
        LocalDate datum,
        LocalTime tijdstip,
        PlaatsType plaats,
        String plaatsBeschrijving,
        GemeenteEnLand adres,
        Boolean meervoudigeZwangerschap
) implements GeboorteJSON, Plaats {

    @Override
    public LocalDate datum() {
        return datum;
    }

}
