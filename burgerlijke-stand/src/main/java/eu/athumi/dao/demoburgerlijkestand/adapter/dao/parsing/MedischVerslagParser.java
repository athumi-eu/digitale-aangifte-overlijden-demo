package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.BijkomendMedischAttestJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischAttestZwangerschapsduurJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.VaststellingOverlijdenJSON;

import java.util.List;

public class MedischVerslagParser {

    public static VaststellingOverlijdenJSON getVaststellingOverlijden(List<MedischVerslag> medischeVerslagen) {
        return getAttestForType(medischeVerslagen, VaststellingOverlijdenJSON.class);
    }

    public static BijkomendMedischAttestJSON getBijkomendMedischAttest(List<MedischVerslag> medischeVerslagen) {
        return getAttestForType(medischeVerslagen, BijkomendMedischAttestJSON.class);
    }

    public static MedischAttestZwangerschapsduurJSON getMedischAttestZwangerschapsduur(List<MedischVerslag> medischeVerslagen) {
        return getAttestForType(medischeVerslagen, MedischAttestZwangerschapsduurJSON.class);
    }

    private static <T> T getAttestForType(List<MedischVerslag> medischeVerslagen, Class<T> clazz) {
        return medischeVerslagen.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .map(clazz::cast)
                .orElse(null);
    }

}
