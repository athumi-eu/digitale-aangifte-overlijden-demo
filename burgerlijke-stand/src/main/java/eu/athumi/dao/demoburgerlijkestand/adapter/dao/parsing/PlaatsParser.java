package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.*;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.MoederOfOudsteOuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.MoederVaststellingJSON;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaatsParser {

    public static String parseAdres(AdresJSON adres) {
        if (Objects.isNull(adres)) {
            return null;
        }

        var gemeenteEnLandString = new GemeenteEnLand(
                new Gemeente(adres.niscode(), adres.gemeentenaam()),
                adres.land(), adres.buitenlandsAdres()
        ).toString();
        return Stream.of(adres.straat(), adres.huisnummer(), adres.bus(), gemeenteEnLandString).filter(Objects::nonNull).collect(Collectors.joining(", "));
    }

    public static String parseAdres(AdresOverlijdenJSON adres) {
        if (Objects.isNull(adres)) {
            return null;
        }

        var gemeenteString = new Gemeente(adres.niscode(), adres.gemeentenaam()
        ).toString();
        return Stream.of(adres.straat(), adres.huisnummer(), adres.bus(), gemeenteString, adres.beschrijving()).filter(Objects::nonNull).collect(Collectors.joining(", "));
    }

    public static String parseLocatie(LocatieJSON locatieJSON) {
        if (Objects.isNull(locatieJSON.locatie())) {
            return locatieJSON.andereLocatie();
        } else if (Objects.equals(locatieJSON.locatie(), "ANDERE")) {
            return locatieJSON.locatie() + ": " + locatieJSON.andereLocatie();
        }
        return locatieJSON.locatie();
    }

    public static String getVerblijfplaatsVoorOuder(OuderJSON ouder) {
        return Optional.ofNullable(ouder)
                .filter(MoederOfOudsteOuderJSON.class::isInstance)
                .map(MoederOfOudsteOuderJSON.class::cast)
                .map(MoederOfOudsteOuderJSON::verblijfplaats)
                .map(GemeenteEnLandJSON::toString)
                .orElse("-");
    }

    public static String getVerblijfplaatsVoorOuder(MoederVaststellingJSON moederVaststelling) {
        return Optional.ofNullable(moederVaststelling).map(MoederVaststellingJSON::verblijfplaats).map(GemeenteEnLandJSON::toString).orElse("-");
    }

}
