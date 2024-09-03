package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Type {
    @JsonProperty("@type")
    String type();
}
