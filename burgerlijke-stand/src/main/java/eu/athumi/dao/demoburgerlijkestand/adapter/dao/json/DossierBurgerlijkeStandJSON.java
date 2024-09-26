package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenJSON;

import java.net.URI;
import java.time.LocalDateTime;

public record DossierBurgerlijkeStandJSON(
    String id,
    DossierStatus dossierStatus,
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
    OverlijdensToestandJSON medischeToestand,
    LocalDateTime afgeslotenOp,
    LocalDateTime heropendOp,
    LocalDateTime ingediendOp
) implements Type {
    @Override
    public String type() {
        return "Persoon";
    }

}
