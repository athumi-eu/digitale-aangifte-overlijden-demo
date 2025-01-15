package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.BijkomendMedischAttestJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.VaststellingOverlijdenJSON;

import java.util.List;
import java.util.Objects;

public class MedischVerslagParser {

    public static VaststellingOverlijdenJSON getVaststellingOverlijden(List<MedischVerslag> medischeVerslagen) {
        return medischeVerslagen.stream()
                .filter(verslag -> Objects.equals(verslag.type(), "MedischVerslagOverlijden"))
                .findFirst()
                .map(VaststellingOverlijdenJSON.class::cast)
                .orElse(null);
    }

    public static BijkomendMedischAttestJSON getBijkomendMedischAttest(List<MedischVerslag> medischeVerslagen) {
        return medischeVerslagen.stream()
                .filter(verslag -> Objects.equals(verslag.type(), "BijkomendMedischAttest"))
                .findFirst()
                .map(BijkomendMedischAttestJSON.class::cast)
                .orElse(null);
    }

}
