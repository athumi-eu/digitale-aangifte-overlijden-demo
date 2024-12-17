package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte;

import java.time.LocalDate;

public record GeboorteOuderDanEenJaarJSON(LocalDate datum) implements GeboorteJSON {


    @Override
    public LocalDate datum() {
        return datum;
    }
}
