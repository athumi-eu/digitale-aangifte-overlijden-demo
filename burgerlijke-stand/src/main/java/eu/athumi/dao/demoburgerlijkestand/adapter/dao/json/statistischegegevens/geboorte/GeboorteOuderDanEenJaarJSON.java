package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;

import java.time.LocalDate;

public record GeboorteOuderDanEenJaarJSON(LocalDate datum,
        GemeenteEnLand adres) implements GeboorteJSON {


    @Override
    public LocalDate datum() {
        return datum;
    }
}
