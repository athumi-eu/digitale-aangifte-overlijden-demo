package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.rijksregister;

public record VerrijkingRijksregisterJSON(
    VerrijkingRijksregisterPersoonJSON overledene,
    VerrijkingRijksregisterPersoonJSON moederOfOudsteOuder,
    VerrijkingRijksregisterPersoonJSON vaderOfJongsteOuder
) {}
