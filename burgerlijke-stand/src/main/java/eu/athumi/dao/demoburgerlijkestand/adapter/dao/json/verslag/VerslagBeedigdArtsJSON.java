package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.InwonerschapJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public class VerslagBeedigdArtsJSON implements Type {

    private final String id;
    private final String kboNummer;
    private final String naam;
    private final String voornaam;
    private final Geslacht geslacht;
    private final String rijksregisternummer;
    private final InwonerschapJSON inwonerschap;
    private final OverlijdenJSON overlijden;
    private final GeboorteJSON geboorte;
    private final OverlijdensToestandJSON medischeToestand;

    public VerslagBeedigdArtsJSON(
        String id,
        String kboNummer,
        String naam,
        String voornaam,
        Geslacht geslacht,
        String rijksregisternummer,
        InwonerschapJSON inwonerschap,
        OverlijdenJSON overlijden,
        GeboorteJSON geboorte,
        OverlijdensToestandJSON medischeToestand
    ) {
        this.id = id;
        this.kboNummer = kboNummer;
        this.naam = naam;
        this.voornaam = voornaam;
        this.geslacht = geslacht;
        this.rijksregisternummer = rijksregisternummer;
        this.inwonerschap = inwonerschap;
        this.overlijden = overlijden;
        this.geboorte = geboorte;
        this.medischeToestand = medischeToestand;
    }

    @Override
    public String type() {
        return "VerslagBeedigdArts";
    }
}
