package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.MoederOfOudsteOuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OuderJSON;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public class PlaatsParser {

    public static String parseAdres(AdresJSON adres) {
        if (Objects.isNull(adres)) {
            return null;
        }
        var straat = Objects.isNull(adres.straat()) ? "" : adres.straat();
        var huisnummer = Objects.isNull(adres.huisnummer()) ? "" : adres.huisnummer();
        var bus = Objects.isNull(adres.bus()) ? "" : adres.bus();
        var niscode = Objects.isNull(adres.niscode()) ? "" : adres.niscode();
        return straat + " " + huisnummer + " " + bus + ", niscode: " + niscode;
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
        if (Objects.isNull(ouder)) {
            return "-";
        }
        var vrouwelijkeOuder = (MoederOfOudsteOuderJSON) ouder;
        var gemeente = ofNullable(vrouwelijkeOuder.verblijfplaats()).map(eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON::gemeente).orElse(null);
        var land = ofNullable(vrouwelijkeOuder.verblijfplaats()).map(eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON::land).orElse(null);
        return Objects.isNull(gemeente) ? (Objects.isNull(land) ? "-" : land.naam()) : gemeente.naam();
    }

}
