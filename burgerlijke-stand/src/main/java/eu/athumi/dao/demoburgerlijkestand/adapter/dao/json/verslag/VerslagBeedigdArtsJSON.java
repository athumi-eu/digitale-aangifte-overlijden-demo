package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.InwonerschapJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;

public class VerslagBeedigdArtsJSON implements Type {

    private String id;
    private String kboNummer;
    private String naam;
    private String voornaam;
    private Geslacht geslacht;
    private String rijksregisternummer;
    private InwonerschapJSON inwonerschap;
    private OverlijdenJSON overlijden;
    private GeboorteJSON geboorte;
    private OverlijdensToestandJSON medischeToestand;

    public VerslagBeedigdArtsJSON() {}

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

    public String id() {
        return id;
    }

    public String kboNummer() {
        return kboNummer;
    }

    public String naam() {
        return naam;
    }

    public String voornaam() {
        return voornaam;
    }

    public Geslacht geslacht() {
        return geslacht;
    }

    public String rijksregisternummer() {
        return rijksregisternummer;
    }

    public InwonerschapJSON inwonerschap() {
        return inwonerschap;
    }

    public OverlijdenJSON overlijden() {
        return overlijden;
    }

    public GeboorteJSON geboorte() {
        return geboorte;
    }

    public OverlijdensToestandJSON medischeToestand() {
        return medischeToestand;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKboNummer(String kboNummer) {
        this.kboNummer = kboNummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public void setInwonerschap(InwonerschapJSON inwonerschap) {
        this.inwonerschap = inwonerschap;
    }

    public void setOverlijden(OverlijdenJSON overlijden) {
        this.overlijden = overlijden;
    }

    public void setGeboorte(GeboorteJSON geboorte) {
        this.geboorte = geboorte;
    }

    public void setMedischeToestand(OverlijdensToestandJSON medischeToestand) {
        this.medischeToestand = medischeToestand;
    }
}
