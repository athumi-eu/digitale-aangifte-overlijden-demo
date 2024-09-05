package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record BeedigdArtsJSON(
    String voornaam,
    String naam,
    String registratie,
    GemeenteJSON plaatsBeediging
)
    implements Type {
    @Override
    public String type() {
        return "Arts";
    }
}
