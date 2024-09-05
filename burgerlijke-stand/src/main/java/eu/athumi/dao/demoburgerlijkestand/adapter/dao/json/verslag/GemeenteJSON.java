package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record GemeenteJSON(String niscode) implements Type {
    @Override
    public String type() {
        return "Adresvoorstelling";
    }
}
