package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

public record VaststellingGegevensJSON(
        String naam,
        String voornaam,
        Geslacht geslacht,
        String rijksregisternummer
) {
}
