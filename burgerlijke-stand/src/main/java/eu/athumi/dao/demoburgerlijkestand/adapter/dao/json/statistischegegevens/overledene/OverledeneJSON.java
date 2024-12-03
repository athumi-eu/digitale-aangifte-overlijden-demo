package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(
                        value = OverledeneJongerDanEenJaarJSON.class,
                        name = "OverledeneJongerDanEenJaarJSON"
                ),
                @JsonSubTypes.Type(
                        value = OverledeneOuderDanEenJaarJSON.class,
                        name = "OverledeneOuderDanEenJaarJSON"
                ),
        }
)
public interface OverledeneJSON {
    Geslacht geslacht();
}
