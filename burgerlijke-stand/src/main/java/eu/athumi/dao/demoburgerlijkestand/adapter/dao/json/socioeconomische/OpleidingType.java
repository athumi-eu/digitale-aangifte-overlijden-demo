package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische;

public enum OpleidingType {
    GEEN ("geen"),
    LAGER_ONDERWIJS ("Lager onderwijs"),
    LAGER_MIDDELBAAR ("Lager middelbaar"),
    HOGER_MIDDELBAAR ("Hoger middelbaar"),
    HOGER_KORT_TYPE ("Hoger onderwijs (kort type)"),
    HOGER_LANG_TYPE ("Hoger onderwijs (lang type)"),
    ANDERE ("Andere"),
    ONBEKEND ("Onbekend");

    private final String label;
    OpleidingType(String label) {
        this.label = label;
    }
    public String getLabel() {return label;}
}
