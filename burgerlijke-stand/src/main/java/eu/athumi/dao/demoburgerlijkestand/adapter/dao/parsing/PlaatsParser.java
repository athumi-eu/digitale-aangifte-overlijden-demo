package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.LocatieJSON;

import java.util.Objects;

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

}
