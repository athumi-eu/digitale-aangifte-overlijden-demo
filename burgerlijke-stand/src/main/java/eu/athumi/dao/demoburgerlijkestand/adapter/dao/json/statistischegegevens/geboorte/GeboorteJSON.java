package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(
                        value = GeboorteOuderDanEenJaarJSON.class,
                        name = "GeboorteOuderDanEenJaarJSON"
                ),
                @JsonSubTypes.Type(
                        value = GeboorteJongerDanEenJaarJSON.class,
                        name = "GeboorteJongerDanEenJaarJSON"
                ),
        }
)
public interface GeboorteJSON {
    LocalDate datum();
}
