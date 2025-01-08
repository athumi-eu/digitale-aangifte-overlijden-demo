package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;


public record BijkomendMedischAttestJSON(
        String osloContextType,
        AardOverlijdenBijkomendMedischAttestType aardOverlijdenBijkomendMedischAttestType
)
        implements MedischVerslag {

}
