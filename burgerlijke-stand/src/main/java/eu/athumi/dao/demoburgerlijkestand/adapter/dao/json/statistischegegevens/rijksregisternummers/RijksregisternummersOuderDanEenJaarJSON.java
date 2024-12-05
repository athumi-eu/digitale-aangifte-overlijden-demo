package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers;

public record RijksregisternummersOuderDanEenJaarJSON(
        RijksregisternummerPersoonJSON overledene
) implements RijksregisternummersJSON {
    @Override
    public RijksregisternummerPersoonJSON overledene() {
        return overledene;
    }
}
