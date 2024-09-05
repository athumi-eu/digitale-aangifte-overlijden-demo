package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;

import java.util.List;
import java.util.Objects;

public record OuderDanEenJaarParser(DossierBurgerlijkeStandJSON dossier) {


    public String naamOverledene() {
        var naam = Objects.isNull(dossier.naam()) ? "" : dossier.naam();
        var voornaam = Objects.isNull(dossier.voornaam()) ? "" : dossier.voornaam();
        return naam + " " + voornaam;
    }

    public String verblijfplaatsOverledene() {
        if (Objects.isNull(dossier.inwonerschap())
                || Objects.isNull(dossier.inwonerschap().verblijfplaats())
                || Objects.isNull(dossier.inwonerschap().verblijfplaats().adres())) {
            return "/";
        }

        return PlaatsParser.parseAdres(dossier.inwonerschap().verblijfplaats().adres());
    }

    public String naamPartner() {
        if (Objects.isNull(dossier.huwelijk())
                || Objects.isNull(dossier.huwelijk().persoonsGegevens())) {
            return "/";
        }
        var naam = Objects.isNull(dossier.huwelijk().persoonsGegevens().naam()) ? "" : dossier.huwelijk().persoonsGegevens().naam();
        var voornaam = Objects.isNull(dossier.huwelijk().persoonsGegevens().voornaam()) ? "" : dossier.huwelijk().persoonsGegevens().voornaam();
        return naam + " " + voornaam;
    }

    public OverlijdenParser detailsOverlijden() {
        return new OverlijdenParser(dossier.overlijden(), MedischVerslagParser.getVaststellingOverlijden(dossier.medischeToestand().medischeVerslagen()));
    }

    public List<String> bezwaren() {
        var medischVerslag = MedischVerslagParser.getVaststellingOverlijden(dossier.medischeToestand().medischeVerslagen());
        if (Objects.isNull(medischVerslag.bezwaar())
        || medischVerslag.bezwaar().isEmpty()) {
            return List.of();
        }
        return medischVerslag.bezwaar().stream().map(Enum::toString).toList();
    }

    public List<String> risicos() {
        var medischVerslag = MedischVerslagParser.getVaststellingOverlijden(dossier.medischeToestand().medischeVerslagen());
        if (Objects.isNull(medischVerslag.risico())
                || medischVerslag.risico().isEmpty()) {
            return List.of();
        }
        return medischVerslag.risico().stream().map(Enum::toString).toList();
    }

}
