package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.VaststellingOverlijdenJSON;

import java.util.List;
import java.util.Objects;

public class MedischVerslagParser {

    public static VaststellingOverlijdenJSON getVaststellingOverlijden(List<VaststellingOverlijdenJSON> medischeVerslagen) {
        return medischeVerslagen.stream().filter(verslag -> Objects.equals(verslag.type(), "VaststellingOverlijden")).findFirst().orElse(null);
    }

}
