package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.BevallingToestandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.MoederJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;

import java.util.Objects;
import java.util.Optional;

public record GeboorteParser(GeboorteJSON geboorte, OverlijdenJSON overlijden, MoederJSON moeder) {

    public String tijdstipGeboorte() {
        if (Objects.isNull(geboorte)
                || Objects.isNull(geboorte.datum())) {
            return "/";
        }

        return TijdstipParser.parseDateString(geboorte.datum(), geboorte().uur());
    }

    public String levendGeboren() {
        if (Objects.isNull(overlijden) || Objects.isNull(overlijden.doodgeboorte())) {
            return "/";
        }
        return overlijden.doodgeboorte() ? "Dood geboren" : "Levend geboren";
    }

    public String plaatsGeboorte() {
        if (Objects.isNull(geboorte.plaats())) {
            return "/";
        }
        return PlaatsParser.parseLocatie(geboorte.plaats());
    }

    public String adresGeboorte() {
        return Optional.ofNullable(geboorte).map(GeboorteJSON::adres).map(GemeenteEnLand::toString).orElse("/");
    }

    public String meervoudigeZwangerschap() {
        BevallingToestandJSON toestand = heeftToestand(moeder);
        if (Objects.isNull(toestand)) {
            return "/";
        }
        return Objects.equals(toestand.meerling(), BevallingToestandJSON.JaNeen.Ja) ? "ja" : "neen";
    }

    public String totaalAantalGeboorten() {
        BevallingToestandJSON toestand = heeftToestand(moeder);
        if (Objects.isNull(toestand)
                || Objects.isNull(toestand.aantalGeboorten())) {
            return "/";
        }
        return toestand.aantalGeboorten().toString();
    }

    private BevallingToestandJSON heeftToestand(MoederJSON moeder) {
        if (
                Objects.isNull(moeder) ||
                        Objects.isNull(moeder.persoonsGegevens()) ||
                        Objects.isNull(moeder.persoonsGegevens().bevalling()) ||
                        Objects.isNull(moeder.persoonsGegevens().bevalling().bevallingToestand())) {
            return null;
        }
        return moeder.persoonsGegevens().bevalling().bevallingToestand();
    }

    public String rang() {
        if (Objects.isNull(geboorte)
                || Objects.isNull(geboorte.geboorteToestand())
                || Objects.isNull(geboorte.geboorteToestand().rang())) {
            return "/";
        }
        return geboorte.geboorteToestand().rang().toString();
    }

    public VerdelingVolgensGeslachtParser.VerdelingVolgensGeslacht aantalLevendGeboren() {
        BevallingToestandJSON toestand = heeftToestand(moeder);
        if (Objects.isNull(toestand)) {
            return new VerdelingVolgensGeslachtParser.VerdelingVolgensGeslacht("/", "/", "/");
        }
        return VerdelingVolgensGeslachtParser.verdeling(toestand.aantalLevendGeboren());
    }

    public VerdelingVolgensGeslachtParser.VerdelingVolgensGeslacht aantalDoodGeboren() {
        BevallingToestandJSON toestand = heeftToestand(moeder);
        if (Objects.isNull(toestand)) {
            return new VerdelingVolgensGeslachtParser.VerdelingVolgensGeslacht("/", "/", "/");
        }
        return VerdelingVolgensGeslachtParser.verdeling(toestand.aantalDoodgeboren());
    }


}
