package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking;

public record DossierVerrijkingJSON(
    OverledeneJSON overledene,
    MoederJSON moeder,
    VaderOfMeeMoederJSON vaderOfMeeMoeder,
    PvOfSysteemNummerJSON pvOfSystemNummer
) {}
