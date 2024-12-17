package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(
                        value = RijksregisternummersJongerDanEenJaarJSON.class,
                        name = "RijksregisternummersJongerDanEenJaarJSON"
                ),
                @JsonSubTypes.Type(
                        value = RijksregisternummersOuderDanEenJaarJSON.class,
                        name = "RijksregisternummersOuderDanEenJaarJSON"
                ),
        }
)
public interface RijksregisternummersJSON {
    RijksregisternummerPersoonJSON overledene();
}
