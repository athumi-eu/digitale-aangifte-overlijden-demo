package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden;


import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Plaats;

import java.util.List;

public record OverlijdenJSON(TijdstipJSON tijdstip, @JsonIgnore List<Plaats> plaats, boolean doodgeboorte)
    implements Type {
    @Override
    public String type() {
        return "Overlijden";
    }
}
