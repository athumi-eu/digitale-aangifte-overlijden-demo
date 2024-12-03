package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;

import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = VaderOfJongsteOuder.class, name = "VaderOfJongsteOuder"),
                @JsonSubTypes.Type(value = MoederOfOudsteOuderJSON.class, name = "MoederOfOudsteOuderJSON"),
        }
)
public interface OuderJSON {
    NationaliteitJSON nationaliteit();
    GeboorteJSON geboorte();
    Geslacht geslacht();
    Set<NationaliteitJSON> oorspronkelijkeNationaliteit();
}
