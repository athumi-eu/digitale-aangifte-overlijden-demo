package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers;

public record RijksregisternummersJongerDanEenJaarJSON(
        RijksregisternummerPersoonJSON overledene,
        RijksregisternummerPersoonJSON moederOfOudsteOuder,
        RijksregisternummerPersoonJSON vaderOfJongsteOuder
) implements RijksregisternummersJSON {
    @Override
    public RijksregisternummerPersoonJSON overledene() {
        return overledene;
    }
}
