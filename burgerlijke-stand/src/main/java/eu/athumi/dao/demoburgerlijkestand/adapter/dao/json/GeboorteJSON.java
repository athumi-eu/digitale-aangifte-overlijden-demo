package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;

import java.time.LocalDateTime;

public record GeboorteJSON(
        LocalDateTime datum,
        LocatieJSON plaats,
        GeboorteToestandJSON geboorteToestand
)
        implements Type {

    @Override
    public String type() {
        return "Geboorte";
    }
}
