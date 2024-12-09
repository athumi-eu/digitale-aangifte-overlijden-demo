package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische;

public enum OnderwijsType {
    BEROEPS("beroeps"),
    TECHNISCH("technisch"),
    ALGEMEEN("algemeen");



    private final String label;
    OnderwijsType(String label) {
        this.label = label;
    }
    public String getLabel() {return label;}
}
