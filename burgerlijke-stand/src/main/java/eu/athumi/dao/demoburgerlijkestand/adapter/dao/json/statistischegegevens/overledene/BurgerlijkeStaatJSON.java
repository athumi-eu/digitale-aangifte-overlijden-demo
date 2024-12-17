package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;

import java.time.LocalDate;

public record BurgerlijkeStaatJSON(BurgerlijkeStaatJSONType type,
                                   String huwelijksDatum,
                                   LocalDate geboortedatumPartner) {
}
