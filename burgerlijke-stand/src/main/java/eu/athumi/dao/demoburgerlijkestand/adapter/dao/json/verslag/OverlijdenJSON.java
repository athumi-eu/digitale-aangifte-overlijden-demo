package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.AdresJSON;

import java.util.List;

public record OverlijdenJSON(TijdstipJSON tijdstip, List<AdresJSON> plaats) implements Type {
    @Override
    public String type() {
        return "Overlijden";
    }
}
