package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({ @JsonSubTypes.Type(AdresJSON.class), @JsonSubTypes.Type(LocatieJSON.class) })
public interface Plaats {
    @JsonProperty("@type")
    default String type() {
        return "Adresvoorstelling";
    }
}
