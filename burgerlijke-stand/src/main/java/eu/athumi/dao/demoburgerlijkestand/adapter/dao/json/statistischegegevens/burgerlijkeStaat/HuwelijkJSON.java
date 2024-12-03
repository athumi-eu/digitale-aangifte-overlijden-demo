package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = HuwelijkOverledeneJSON.class)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(HuwelijkOuderJSON.class),
        @JsonSubTypes.Type(HuwelijkOverledeneJSON.class),
    }
)
public interface HuwelijkJSON {
    LocalDate datum();
}
