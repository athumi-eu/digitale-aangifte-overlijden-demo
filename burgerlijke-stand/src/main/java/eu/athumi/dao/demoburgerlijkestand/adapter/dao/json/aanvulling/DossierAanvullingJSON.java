package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.aanvulling;

public record DossierAanvullingJSON(
    OverledeneJSON overledene,
    MoederJSON moeder,
    VaderOfMeeMoederJSON vaderOfMeeMoeder,
    PvOfSysteemNummerJSON pvOfSystemNummer
) {}
