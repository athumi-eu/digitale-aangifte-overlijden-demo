package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.OverlijdensToestandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederJSON;

import java.util.Optional;

public class MedischAttestZwangerschapsduurParser {
    private final DossierBurgerlijkeStandJSON dossier;
    private final GeboorteParser parser;

    public MedischAttestZwangerschapsduurParser(DossierBurgerlijkeStandJSON dossier) {
        this.dossier = dossier;
        this.parser = new GeboorteParser(dossier.geboorte(), dossier.overlijden(), dossier.moeder());
    }

    public boolean isPresent() {
        return Optional.ofNullable(dossier)
                .map(DossierBurgerlijkeStandJSON::medischeToestand)
                .map(OverlijdensToestandJSON::medischeVerslagen)
                .map(MedischVerslagParser::getMedischAttestZwangerschapsduur)
                .isPresent();
    }

    public int aantalDagenZwanger() {
        return MedischVerslagParser.getMedischAttestZwangerschapsduur(dossier.medischeToestand().medischeVerslagen()).aantalDagenZwangerschap();
    }

    public String geslacht() {
        return dossier.geslacht().toString();
    }

    public String tijdstip() {
        return parser.tijdstipGeboorte();
    }

    public String plaats() {
        return parser.adresGeboorte();
    }

    public String moeder() {
        return Optional.ofNullable(dossier.moeder())
                .map(MoederJSON::persoonsGegevens)
                .map(m -> m.voornaam() + " " + m.naam())
                .orElse("/");
    }
}
