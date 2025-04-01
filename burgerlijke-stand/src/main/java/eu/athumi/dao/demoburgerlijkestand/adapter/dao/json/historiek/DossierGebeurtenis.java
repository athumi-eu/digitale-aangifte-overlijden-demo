package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.historiek;

import java.time.LocalDateTime;
import java.util.Comparator;

public record DossierGebeurtenis(GebeurtenisType type, GebeurtenisReden reden, String message, LocalDateTime tijdstip)
        implements Comparable<DossierGebeurtenis> {

    @Override
    public int compareTo(DossierGebeurtenis o) {
        return Comparator.comparing(DossierGebeurtenis::tijdstip)
                .reversed()
                .thenComparing(DossierGebeurtenis::type)
                .compare(this, o);
    }
}
