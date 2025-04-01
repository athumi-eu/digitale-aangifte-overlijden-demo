package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;


public record AdresOverlijdenJSON(
        String straat,
        String huisnummer,
        String bus,
        String postcode,
        String niscode,
        String gemeentenaam,
        String beschrijving
)
        implements Plaats {
}
