package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens;

public record TableRow(String label, String RR, String vaststelling, String uitvaart, String abs,
                       String departementZorg) {

    public static TableRow empty(){
        return new TableRow("_","_","_","_","_", "_");
    }
}
