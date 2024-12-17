package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.VerdelingVolgensGeslachtJSON;

public record MeervoudigeZwangerschapJSON(
        Integer totaalAantalKinderen,
        Integer rangOverledene,
        VerdelingVolgensGeslachtJSON aantalLevendGeboren,
        VerdelingVolgensGeslachtJSON aantalDoodGeboren
) {}
