package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.historiek;

public enum GebeurtenisReden {
    //UITVAART_ONTKOPPELD
    UITVAART_ONTKOPPELD_DOOR_LOKAAL_BESTUUR(GebeurtenisType.UITVAART_ONTKOPPELD),
    DOSSIER_GEANNULEERD_DOOR_UITVAART(GebeurtenisType.UITVAART_ONTKOPPELD),

    //DOSSIER HEROPEND
    MANUEEL_HEROPEND(GebeurtenisType.DOSSIER_HEROPEND),
    SOCIOECONOMISCHE_GEGEVENS_AANGEPAST(GebeurtenisType.DOSSIER_HEROPEND),
    INLICHTINGENFICHE_AANGEPAST(GebeurtenisType.DOSSIER_HEROPEND);

    private final GebeurtenisType vanToepassingOp;

    GebeurtenisReden(GebeurtenisType vanToepassingOp) {
        this.vanToepassingOp = vanToepassingOp;
    }
}