package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;

import java.time.LocalDate;

public record GeboorteJSON(
        // TODO fix correctly
        LocalDate datum,
        LocatieJSON plaats,
        GeboorteToestandJSON geboorteToestand
)
        implements Type {

    @Override
    public String type() {
        return "Geboorte";
    }
}
