package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.historiek;

import java.time.LocalDateTime;
import java.util.Comparator;

public record DossierGebeurtenis(GebeurtenisType type, LocalDateTime tijdstip, String message)
        implements Comparable<DossierGebeurtenis> {
    public DossierGebeurtenis(GebeurtenisType type, String message) {
        this(type, LocalDateTime.now(), message);
    }

    @Override
    public int compareTo(DossierGebeurtenis o) {
        return Comparator.comparing(DossierGebeurtenis::tijdstip)
                .reversed()
                .thenComparing(DossierGebeurtenis::type)
                .compare(this, o);
    }
}
