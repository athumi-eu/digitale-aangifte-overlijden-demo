package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

import java.time.LocalDateTime;

public record TijdstipJSON(LocalDateTime datum, String beschrijvingTijdstip) implements Type {
    @Override
    public String type() {
        return "TijdIndicatie";
    }
}
