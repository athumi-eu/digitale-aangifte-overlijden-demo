package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;

public record MeervoudigeZwangerschapDTO(
    Integer totaalAantalKinderen,
    Integer rangOverledene,
    VerdelingVolgensGeslachtDTO aantalLevendGeboren,
    VerdelingVolgensGeslachtDTO aantalDoodGeboren
) {}
