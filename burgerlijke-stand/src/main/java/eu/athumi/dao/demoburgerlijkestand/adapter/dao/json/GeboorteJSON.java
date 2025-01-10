package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;

import java.time.LocalDate;
import java.time.LocalTime;

public record GeboorteJSON(
        LocalDate datum,
        LocalTime uur,
        LocatieJSON plaats,
        GemeenteEnLand adres,
        GeboorteToestandJSON geboorteToestand
)
        implements Type {

    @Override
    public String type() {
        return "Geboorte";
    }
}
