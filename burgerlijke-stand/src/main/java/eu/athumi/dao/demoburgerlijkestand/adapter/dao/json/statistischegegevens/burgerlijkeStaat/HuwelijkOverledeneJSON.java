package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat;

import java.time.LocalDate;

public record HuwelijkOverledeneJSON(
        LocalDate datum,
        LocalDate geboorteDatumOverlevendePartner
) implements HuwelijkJSON {

    @Override
    public LocalDate datum() {
        return datum;
    }

}
