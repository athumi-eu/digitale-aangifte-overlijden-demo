package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenJSON;

import java.net.URI;

public record DossierBurgerlijkeStandJSON(
        String id,
        DossierStatus dossierStatus,
        String kboNummer,
        URI verslagDetailURL,
        VaststellingType vaststellingType,
        String naam,
        String voornaam,
        Geslacht geslacht,
        String rijksregisternummer,
        InwonerschapJSON inwonerschap,
        OverlijdenJSON overlijden,
        HuwelijkJSON huwelijk,
        MoederJSON moeder,
        GeboorteJSON geboorte,
        OverlijdensToestandJSON medischeToestand
) implements Type {


    @Override
    public String type() {
        return "Persoon";
    }

}
