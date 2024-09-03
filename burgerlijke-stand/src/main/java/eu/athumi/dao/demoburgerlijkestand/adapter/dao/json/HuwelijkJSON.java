package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


public record HuwelijkJSON(PartnerJSON persoonsGegevens) implements Type {

    @Override
    public String type() {
        return "Huwelijk";
    }
}
