package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.VerblijfplaatsJSON;

public record InwonerschapJSON(VerblijfplaatsJSON verblijfplaats) implements Type {
    @Override
    public String type() {
        return "Inwonerschap";
    }

}
