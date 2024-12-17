package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = HuwelijkOuderJSON.class, name = "HuwelijkOuderJSON"),
                @JsonSubTypes.Type(value = HuwelijkOverledeneJSON.class, name = "HuwelijkOverledeneJSON"),
        }
)
public interface HuwelijkJSON {
    LocalDate datum();
}
