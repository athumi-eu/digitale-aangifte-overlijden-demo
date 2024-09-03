package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


public record ArtsJSON(String riziv, String naam, String voornaam) implements Type {

    @Override
    public String type() {
        return "Arts";
    }
}
