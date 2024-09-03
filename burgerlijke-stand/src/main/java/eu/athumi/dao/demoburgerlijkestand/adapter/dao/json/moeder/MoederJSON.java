package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record MoederJSON(String gezinsrelatietype, MoederDetailJSON persoonsGegevens)
    implements Type {
    @Override
    public String type() {
        return "MoederRelatie";
    }
}