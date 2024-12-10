package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische;
public record Beroep(String code, String omschrijving)  {

    @Override
    public String toString() {
        return "{\"code\": \"" + code + "\", \"omschrijving\": \"" + omschrijving + "\"}";
    }
}
