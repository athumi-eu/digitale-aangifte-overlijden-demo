package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public record VerblijfplaatsJSON(AdresJSON adres) implements Type {
    @Override
    public String type() {
        return "Verblijfplaats";
    }

}
