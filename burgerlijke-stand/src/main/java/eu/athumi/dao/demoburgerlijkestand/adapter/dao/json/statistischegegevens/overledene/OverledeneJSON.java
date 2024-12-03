package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = OverledeneJongerDanEenJaarJSON.class)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(OverledeneJongerDanEenJaarJSON.class),
        @JsonSubTypes.Type(OverledeneOuderDanEenJaarJSON.class),
    }
)
public interface OverledeneJSON {
    Geslacht geslacht();
}
