package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;


public record AdresJSON(
    String straat,
    String huisnummer,
    String bus,
    String postcode,
    String niscode
)
    implements Plaats {
}
