package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

public record GeboorteToestandJSON(Integer rang) implements Type {

    @Override
    public String type() {
        return "GeboorteToestand";
    }
}
