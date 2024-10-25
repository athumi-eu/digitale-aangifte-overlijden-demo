package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.PlaatsParser;

public record NabestaandeJSON(
    String rijksregisternummer,
    String voornaam,
    String naam,
    AdresJSON adres,
    String anderLandNisCode,
    String anderLandAdres
){
    public String parsedAdres() {
        return PlaatsParser.parseAdres(adres);
    }
}
