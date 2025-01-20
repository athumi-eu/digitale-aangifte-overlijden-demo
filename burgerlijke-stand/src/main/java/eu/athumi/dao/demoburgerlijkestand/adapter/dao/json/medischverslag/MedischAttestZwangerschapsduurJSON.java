package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;

public record MedischAttestZwangerschapsduurJSON(
        String type,
        Integer aantalDagenZwangerschap
)  implements MedischVerslag {}