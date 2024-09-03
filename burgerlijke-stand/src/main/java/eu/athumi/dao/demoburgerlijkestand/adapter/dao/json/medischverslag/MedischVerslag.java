package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface MedischVerslag {
    @JsonProperty("@type")
    default String type() {
        return "MedischVerslagOverlijden";
    }
}
