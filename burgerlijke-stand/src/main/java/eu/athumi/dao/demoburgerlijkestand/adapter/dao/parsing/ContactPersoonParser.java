package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.ContactPersoonJSON;

import java.util.Objects;

public record ContactPersoonParser(ContactPersoonJSON json) {

    public String volledigeNaam() {
        if ((Objects.isNull(json) || Objects.isNull(json.naam())) && (Objects.isNull(json) || Objects.isNull(json.voornaam()))) {
            return "/";
        }
        String volledigeNaam = json.voornaam() + " " + json.naam();
        return volledigeNaam.trim();
    }

    public String email() {
        if (Objects.isNull(json) || Objects.isNull(json.email())) {
            return "/";
        }
        return json.email();
    }

    public String vestigingNaam() {
        if (Objects.isNull(json) || Objects.isNull(json.vestigingNaam())) {
            return "/";
        }
        System.out.println("vestigingNaam: " + json.vestigingNaam());
        return json.vestigingNaam();
    }
}
