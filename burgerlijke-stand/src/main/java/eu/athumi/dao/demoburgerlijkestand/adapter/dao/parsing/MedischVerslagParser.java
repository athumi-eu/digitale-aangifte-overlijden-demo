package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.BijkomendMedischAttestJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischAttestZwangerschapsduurJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.VaststellingOverlijdenJSON;

import java.util.List;
import java.util.Objects;

public class MedischVerslagParser {

    public static VaststellingOverlijdenJSON getVaststellingOverlijden(List<MedischVerslag> medischeVerslagen) {
        return getAttestForType(medischeVerslagen, "MedischVerslagOverlijden", VaststellingOverlijdenJSON.class);
    }

    public static BijkomendMedischAttestJSON getBijkomendMedischAttest(List<MedischVerslag> medischeVerslagen) {
        return getAttestForType(medischeVerslagen, "BijkomendMedischAttest", BijkomendMedischAttestJSON.class);
    }

    public static MedischAttestZwangerschapsduurJSON getMedischAttestZwangerschapsduur(List<MedischVerslag> medischeVerslagen) {
        return getAttestForType(medischeVerslagen, "MedischAttestZwangerschapsduur", MedischAttestZwangerschapsduurJSON.class);
    }

    private static <T> T getAttestForType(List<MedischVerslag> medischeVerslagen, String identifier, Class<T> clazz) {
        return medischeVerslagen.stream()
                .filter(verslag -> Objects.equals(verslag.type(), identifier))
                .findFirst()
                .map(clazz::cast)
                .orElse(null);
    }

}
