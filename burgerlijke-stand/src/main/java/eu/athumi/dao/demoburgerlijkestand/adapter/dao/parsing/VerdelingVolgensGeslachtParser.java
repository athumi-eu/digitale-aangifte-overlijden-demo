package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.moeder.VerdelingVolgensGeslachtJSON;

import java.util.Objects;

public class VerdelingVolgensGeslachtParser {

    public static VerdelingVolgensGeslacht verdeling(VerdelingVolgensGeslachtJSON verdeling) {
        if (Objects.isNull(verdeling)) {
            return new VerdelingVolgensGeslacht("/", "/", "/");
        }
        return new VerdelingVolgensGeslacht(
                Objects.isNull(verdeling.mannelijk()) ? "/" : verdeling.mannelijk().toString(),
                Objects.isNull(verdeling.vrouwelijk()) ? "/" : verdeling.vrouwelijk().toString(),
                Objects.isNull(verdeling.onbepaald()) ? "/" : verdeling.onbepaald().toString());
    }

    public record VerdelingVolgensGeslacht(String mannelijk, String vrouwelijk, String onbepaald) {
    }

}
