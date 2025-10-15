package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;

public record BurgerlijkeStaatJSON(BurgerlijkeStaatJSONType type,
                                   String huwelijksDatum,
                                   String geboortedatumPartner) {
}
