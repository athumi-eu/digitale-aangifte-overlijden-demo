package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;

public record StatistischeGegevensParser(StatistischeGegevensJSON statistischeGegevens) {

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                "-",
                "-",
                "-",
                "-",
                statistischeGegevens.persoonsgegevens().departementZorg().overledene().geslacht().name()
                );
    }

    public record TableRow(String label, String RR,String vaststelling,String uitvaart,String abs,String departementZorg){};
}
