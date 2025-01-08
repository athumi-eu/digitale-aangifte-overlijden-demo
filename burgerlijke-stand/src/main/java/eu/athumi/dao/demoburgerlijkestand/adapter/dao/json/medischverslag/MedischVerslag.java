package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.MedischVerslagBeedigdArtsJSON;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(
                        value = BijkomendMedischAttestJSON.class,
                        name = "BijkomendMedischAttest"
                ),
                @JsonSubTypes.Type(
                        value = MedischVerslagBeedigdArtsJSON.class,
                        name = "VerslagBeedigdArts"
                ),
                @JsonSubTypes.Type(
                        value = VaststellingOverlijdenJSON.class,
                        name = "MedischVerslagOverlijden"
                ),
        }
)
public interface MedischVerslag {
    @JsonProperty("@type")
    default String type() {
        return "MedischVerslagOverlijden";
    }
}
