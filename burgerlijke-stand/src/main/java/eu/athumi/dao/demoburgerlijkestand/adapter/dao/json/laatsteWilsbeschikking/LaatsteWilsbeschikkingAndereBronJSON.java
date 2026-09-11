package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.laatsteWilsbeschikking;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.time.LocalDate;

public record LaatsteWilsbeschikkingAndereBronJSON(
        LocalDate dateOfDeclaration
) {
    public String parsedDateOfDeclaration() {
        return TijdstipParser.parseLocalDate(dateOfDeclaration);
    }
}
