package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking;

public record MoederJSON(
    String rijksregisternummer,
    Boolean nietVerblijfshouder,
    Boolean nietGekend
) {}
