package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.InwonerschapJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record MoederDetailJSON(
    String naam,
    String voornaam,
    InwonerschapJSON inwonerschap,
    BevallingJSON bevalling
)
    implements Type {

    @Override
    public String type() {
        return "Persoon";
    }
}
