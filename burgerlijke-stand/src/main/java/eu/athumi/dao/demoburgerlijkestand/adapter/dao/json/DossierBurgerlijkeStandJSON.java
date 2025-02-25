package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche.InlichtingenficheJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.aanvulling.DossierAanvullingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verantwoordelijk.LokaalBestuurVanBehandeling;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.rijksregister.VerrijkingRijksregisterJSON;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalDateTime;

public record DossierBurgerlijkeStandJSON(
        String id,
        DossierStatus dossierStatus,
        LokaalBestuurVanBehandeling lokaalBestuurVanBehandeling,
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
        LocalDateTime verwijderdOp,
        LocalDateTime ingediendOp,
        DossierAanvullingJSON aanvulling,
        VerrijkingRijksregisterJSON verrijkingRijksregister,
        UitvaartOndernemerJSON uitvaartOndernemer,
        UitvaartOndernemerJSON vorigeUitvaartOndernemer,
        InlichtingenficheJSON inlichtingenfiche
) implements Type {
    @Override
    public String type() {
        return "Persoon";
    }

    public String parsedAfgeslotenOp() {
        return parsedDateTime(afgeslotenOp);
    }

    public String parsedHeropendOp() {
        return parsedDateTime(heropendOp);
    }

    public String parsedVerwijderdOp() {
        return parsedDateTime(verwijderdOp);
    }

    public String parsedIngediendOp() {
        return parsedDateTime(ingediendOp);
    }

    private String parsedDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return "/";
        }
        return parseLocalDateTime(localDateTime);
    }
}
