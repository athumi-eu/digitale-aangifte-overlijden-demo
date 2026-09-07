package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;

public record MedischAttestZwangerschapsduurJSON(
        Integer aantalDagenZwangerschap
)  implements MedischVerslag {

    @Override
    public String type() {
        return "MedischAttestZwangerschapsduur";
    }
}