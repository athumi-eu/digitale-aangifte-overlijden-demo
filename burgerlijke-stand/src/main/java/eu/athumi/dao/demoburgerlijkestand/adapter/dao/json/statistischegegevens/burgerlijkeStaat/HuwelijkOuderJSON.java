package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat;

import java.time.LocalDate;

public record HuwelijkOuderJSON(
        LocalDate datum,
        Integer aantalLevendGeboren,
        Integer aantalDoodGeboren
) implements HuwelijkJSON {

    @Override
    public LocalDate datum() {
        return datum;
    }
}
