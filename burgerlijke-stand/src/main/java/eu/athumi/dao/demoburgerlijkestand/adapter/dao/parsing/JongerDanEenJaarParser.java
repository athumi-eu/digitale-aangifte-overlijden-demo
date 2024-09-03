package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;

import java.util.List;
import java.util.Objects;

public record JongerDanEenJaarParser(DossierBurgerlijkeStandJSON dossier) {


    public String naamOverledene() {
        return dossier.naam() + " " + dossier.voornaam();
    }

    public String verblijfplaatsMoeder() {
        if (Objects.isNull(dossier.moeder())
                || Objects.isNull(dossier.moeder().persoonsGegevens())
                || Objects.isNull(dossier.moeder().persoonsGegevens().inwonerschap())
                || Objects.isNull(dossier.moeder().persoonsGegevens().inwonerschap().verblijfplaats())
                || Objects.isNull(dossier.moeder().persoonsGegevens().inwonerschap().verblijfplaats().adres())) {
            return "/";
        }

        return PlaatsParser.parseAdres(dossier.moeder().persoonsGegevens().inwonerschap().verblijfplaats().adres());
    }

    public String naamMoeder() {
        if (Objects.isNull(dossier.moeder())
                || Objects.isNull(dossier.moeder().persoonsGegevens())) {
            return "/";
        }

        return dossier.naam() + " " + dossier.voornaam();
    }

    public OverlijdenParser detailsOverlijden() {
        return new OverlijdenParser(dossier.overlijden(), MedischVerslagParser.getVaststellingOverlijden(dossier.medischeVerslagen()));
    }

    public GeboorteParser geboorteDetails() {
        return  new GeboorteParser(dossier.geboorte(), dossier.overlijden(), dossier.moeder());
    }

    public List<String> bezwaren() {
        var medischVerslag = MedischVerslagParser.getVaststellingOverlijden(dossier.medischeVerslagen());
        if (Objects.isNull(medischVerslag.bezwaar())
        || medischVerslag.bezwaar().isEmpty()) {
            return List.of();
        }
        return medischVerslag.bezwaar().stream().map(Enum::toString).toList();
    }

    public List<String> risicos() {
        var medischVerslag = MedischVerslagParser.getVaststellingOverlijden(dossier.medischeVerslagen());
        if (Objects.isNull(medischVerslag.risico())
                || medischVerslag.risico().isEmpty()) {
            return List.of();
        }
        return medischVerslag.risico().stream().map(Enum::toString).toList();
    }

}
