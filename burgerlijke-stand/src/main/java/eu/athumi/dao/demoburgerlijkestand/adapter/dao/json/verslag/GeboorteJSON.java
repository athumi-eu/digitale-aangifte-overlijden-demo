package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

import java.time.LocalDateTime;

public record GeboorteJSON(LocalDateTime datum, GeboorteLocatieJSON plaats) implements Type {
    @Override
    public String type() {
        return "Geboorte";
    }
}
