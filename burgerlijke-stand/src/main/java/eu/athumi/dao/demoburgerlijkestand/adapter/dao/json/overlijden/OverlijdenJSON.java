package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Plaats;

import java.util.List;

public record OverlijdenJSON(TijdstipJSON tijdstip, List<Plaats> plaats, boolean doodgeboorte)
    implements Type {
    @Override
    public String type() {
        return "Overlijden";
    }
}
