package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record BevallingToestandJSON(
    boolean meerling,
    Integer aantalGeboorten,
    VerdelingVolgensGeslachtJSON aantalLevendGeboren,
    VerdelingVolgensGeslachtJSON aantalDoodgeboren
)
    implements Type {
    @Override
    public String type() {
        return "BevallingToestand";
    }

}
