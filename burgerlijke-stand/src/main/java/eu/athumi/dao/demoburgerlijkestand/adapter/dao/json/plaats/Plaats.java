package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Plaats {
    @JsonProperty("@type")
    default String type() {
        return "Adresvoorstelling";
    }
}
