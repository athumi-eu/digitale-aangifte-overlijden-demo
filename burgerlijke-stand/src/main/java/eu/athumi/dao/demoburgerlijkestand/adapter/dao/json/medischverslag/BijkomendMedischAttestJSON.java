package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;


public record BijkomendMedischAttestJSON(
        String type,
        AardOverlijdenBijkomendMedischAttestType aardOverlijdenBijkomendMedischAttestType
)
        implements MedischVerslag {

}
