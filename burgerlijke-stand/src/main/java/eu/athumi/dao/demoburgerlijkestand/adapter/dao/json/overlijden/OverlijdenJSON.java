package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.AdresOverlijdenJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Plaats;

import java.util.List;

public record OverlijdenJSON(TijdstipJSON tijdstip, List<Plaats> plaats, boolean doodgeboorte,
                             List<OverlijdenAfhandelingOutputJSON> bewijzen)
        implements Type {
    @Override
    public String type() {
        return "Overlijden";
    }

    public AdresOverlijdenJSON getAdresOverlijden() {
        return plaats.stream().filter(json -> json instanceof AdresOverlijdenJSON).map(json -> (AdresOverlijdenJSON) json).findFirst().orElseGet(() -> new AdresOverlijdenJSON(null, null, null, null, null, null, null));
    }

    public LocatieJSON getLocatieOverlijden() {
        return this.plaats.stream().filter(json -> json instanceof LocatieJSON).map(json -> (LocatieJSON) json).findFirst().orElseGet(() -> new LocatieJSON(null, null));
    }

    public OverlijdenAfhandelingOutputJSON getOverlijdenAfhandeling(String type) {
        return bewijzen.stream().filter(t -> t.type().toString().equals(type)).findFirst().orElseGet(() -> new OverlijdenAfhandelingOutputJSON(null, null, null, null, null));
    }
}
