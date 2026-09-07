package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;


public record BijkomendMedischAttestJSON(
        AardOverlijdenBijkomendMedischAttestType aardOverlijdenBijkomendMedischAttestType
)
        implements MedischVerslag {

    @Override
    public String type() {
        return "BijkomendMedischAttest";
    }
}
