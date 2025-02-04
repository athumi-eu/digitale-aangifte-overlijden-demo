package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.Plaats;

import java.util.List;

public record OverlijdenJSON(TijdstipJSON tijdstip, List<Plaats> plaats, boolean doodgeboorte, List<OverlijdenAfhandelingOutputJSON> bewijzen)
        implements Type {
    @Override
    public String type() {
        return "Overlijden";
    }

    public AdresJSON getAdresOverlijden() {
        return plaats.stream().filter(json -> json instanceof AdresJSON).map(json -> (AdresJSON) json).findFirst().orElseGet(() -> new AdresJSON(null, null, null, null, null, null, null, null));
    }

    public LocatieJSON getLocatieOverlijden() {
        return this.plaats.stream().filter(json -> json instanceof LocatieJSON).map(json -> (LocatieJSON) json).findFirst().orElseGet(() -> new LocatieJSON(null, null));
    }

    public OverlijdenAfhandelingOutputJSON getOverlijdenAfhandeling(String type) {
        return bewijzen.stream().filter(t -> t.type().toString().equals(type)).findFirst().orElseGet(() -> new OverlijdenAfhandelingOutputJSON(null, null, null, null, null));
    }
}
