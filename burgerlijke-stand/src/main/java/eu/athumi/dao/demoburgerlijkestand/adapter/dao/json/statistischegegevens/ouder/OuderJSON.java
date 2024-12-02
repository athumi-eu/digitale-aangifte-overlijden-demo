package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;

import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = MannelijkeOuderJSON.class)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(MannelijkeOuderJSON.class),
        @JsonSubTypes.Type(VrouwelijkeOuderJSON.class),
    }
)
public interface OuderJSON {
    NationaliteitJSON nationaliteit();
    GeboorteJSON geboorte();
    Geslacht geslacht();
    BurgerlijkeStaatJSONType burgerlijkeStaat();
    Set<NationaliteitJSON> oorspronkelijkeNationaliteit();
}
