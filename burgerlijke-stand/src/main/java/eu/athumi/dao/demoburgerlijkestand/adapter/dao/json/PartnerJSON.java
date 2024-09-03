package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

public record PartnerJSON(String naam, String voornaam) implements Type {

    @Override
    public String type() {
        return "Persoon";
    }
}
