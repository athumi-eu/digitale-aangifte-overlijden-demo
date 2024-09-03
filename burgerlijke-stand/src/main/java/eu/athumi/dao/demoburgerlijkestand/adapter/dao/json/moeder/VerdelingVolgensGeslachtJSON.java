package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record VerdelingVolgensGeslachtJSON(Integer mannelijk, Integer vrouwelijk, Integer onbepaald)
    implements Type {
    @Override
    public String type() {
        return "BevallingToestand";
    }

}
