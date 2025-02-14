package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederDetailJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteOuderDanEenJaarJSON;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record JongerDanEenJaarParser(DossierBurgerlijkeStandJSON dossier) {


    public UitvaartOndernemerParser uo() {
        return new UitvaartOndernemerParser(dossier.uitvaartOndernemer());
    }

    public UitvaartOndernemerParser vuo() {
        return new UitvaartOndernemerParser(dossier.vorigeUitvaartOndernemer());
    }

    public AanvullingParser aanvulling() {
        return new AanvullingParser(dossier.aanvulling());
    }

    public VerrijkingRijksregisterParser verrijkingRijksregister() {
        return new VerrijkingRijksregisterParser(dossier.verrijkingRijksregister());
    }

    public String naamOverledene() {
        var naam = Objects.isNull(dossier.naam()) ? "" : dossier.naam();
        var voornaam = Objects.isNull(dossier.voornaam()) ? "" : dossier.voornaam();
        return naam + " " + voornaam;
    }

    public String verblijfplaatsMoeder() {
        if (Objects.isNull(dossier.moeder())
                || Objects.isNull(dossier.moeder().persoonsGegevens())
                || Objects.isNull(dossier.moeder().persoonsGegevens().inwonerschap())
                || Objects.isNull(dossier.moeder().persoonsGegevens().inwonerschap().verblijfplaats())
                || Objects.isNull(dossier.moeder().persoonsGegevens().inwonerschap().verblijfplaats().adres())) {
            return null;
        }

        return PlaatsParser.parseAdres(dossier.moeder().persoonsGegevens().inwonerschap().verblijfplaats().adres());
    }

    public String naamMoeder() {
        if (Objects.isNull(dossier.moeder())
                || Objects.isNull(dossier.moeder().persoonsGegevens())) {
            return "/";
        }

        var naam = Objects.isNull(dossier.moeder().persoonsGegevens().naam()) ? "" : dossier.moeder().persoonsGegevens().naam();
        var voornaam = Objects.isNull(dossier.moeder().persoonsGegevens().voornaam()) ? "" : dossier.moeder().persoonsGegevens().voornaam();
        return naam + " " + voornaam;
    }
    private Optional<MoederDetailJSON> moederOpt(){ return Optional.ofNullable(dossier.moeder()).map(MoederJSON::persoonsGegevens); }

    public String geslachtMoeder() {
        return moederOpt().map(MoederDetailJSON::geslacht).map(Objects::toString).orElse("/");
    }

    public String rijksregisterMoeder() {
        return moederOpt().map(MoederDetailJSON::rijksregisternummer).orElse("/");
    }

    public String geboorteAdresMoeder() {
        return moederOpt().map(MoederDetailJSON::geboorte).map(eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.GeboorteJSON::adres).map(Objects::toString).orElse("/");
    }

    public String geboorteDatumMoeder() {
        return moederOpt().map(MoederDetailJSON::geboorte).map(eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.GeboorteJSON::datum).map(g -> g.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).orElse("/");
    }

    public OverlijdenParser detailsOverlijden() {
        return new OverlijdenParser(dossier.overlijden(), MedischVerslagParser.getVaststellingOverlijden(dossier.medischeToestand().medischeVerslagen()));
    }

    public GeboorteParser geboorteDetails() {
        return new GeboorteParser(dossier.geboorte(), dossier.overlijden(), dossier.moeder());
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

    public List<String> maatregelen() {
        var medischVerslag = MedischVerslagParser.getVaststellingOverlijden(dossier.medischeToestand().medischeVerslagen());
        if (Objects.isNull(medischVerslag.maatregel())
                || medischVerslag.maatregel().isEmpty()) {
            return List.of();
        }
        return medischVerslag.maatregel().stream().map(Enum::toString).toList();
    }

    public String bijkomendMedischAttest() {
        var bijkomendMedischAttest = MedischVerslagParser.getBijkomendMedischAttest(dossier.medischeToestand().medischeVerslagen());
        if (Objects.isNull(bijkomendMedischAttest)) {
            return "Niet ingevuld";
        }
        return bijkomendMedischAttest.aardOverlijdenBijkomendMedischAttestType().toString();
    }

}
