package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = GeboorteOuderDanEenJaarJSON.class)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(GeboorteOuderDanEenJaarJSON.class),
        @JsonSubTypes.Type(GeboorteJongerDanEenJaarJSON.class),
    }
)
public interface GeboorteJSON {
    LocalDate datum();
}
