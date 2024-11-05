package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FicheDocumentenParser {
    DossierBurgerlijkeStandJSON dossier;

    public FicheDocumentenParser(DossierBurgerlijkeStandJSON dossier) {
        this.dossier = dossier;
    }

    public record DocumentRenderObject(String label, String opgeladen, String url){}

    public List<DocumentRenderObject> getFicheDocumenten() {
        var docs = new ArrayList<DocumentJSON>();
        docs.addAll(getLocatieDocumenten());
        docs.add(getWettelijkePartnerDocument());
        docs.add(getVerzoekVaderOfMeemoederDocument());
        return docs.stream()
                .filter(Objects::nonNull)
                .map(this::jsonToRenderObject)
                .toList();
    }

    private DocumentRenderObject jsonToRenderObject(DocumentJSON json) {
        var url = String.format("documenten?dossierId=%s&type=%s", dossier.id(), json.type());
        return new DocumentRenderObject(documentTypeToLabel(json.type()), dateToLabel(json.localDateTime()), url);
    }

    private List<DocumentJSON> getLocatieDocumenten() {
        return Optional.ofNullable(dossier.inlichtingenfiche())
                .map(InlichtingenficheJSON::crematie)
                .map(CrematieJSON::bestemmingAs)
                .map(BestemmingAsJSON::locatie)
                .map(LocatieJSON::documenten)
                .orElse(List.of());
    }

    private DocumentJSON getWettelijkePartnerDocument() {
        return Optional.ofNullable(dossier.inlichtingenfiche())
                .map(InlichtingenficheJSON::crematie)
                .map(CrematieJSON::asWettelijkePartner)
                .map(AsWettelijkePartnerJSON::verzoekNabestaande)
                .orElse(null);
    }

    private DocumentJSON getVerzoekVaderOfMeemoederDocument() {
        return Optional.ofNullable(dossier.inlichtingenfiche())
                .map(InlichtingenficheJSON::informatieAkteLevenloosKind)
                .map(InformatieAkteLevenloosKindJSON::verzoekVaderOfMeemoeder)
                .orElse(null);
    }

    String documentTypeToLabel(DocumentType documentType) {
        return switch (documentType) {
            case VERZOEK_NABESTAANDE -> "Verzoek van nabestaanden";
            case TOESTEMMING_EIGENAAR -> "Toestemming eigenaar terrein";
            case VERZOEK_NABESTAANDE_AS_PARTNER -> "Verzoek van nabestaanden ivm as partner";
            case VERZOEK_OPNAME_ALS_VADER_OF_MEEMOEDER -> "Ondertekend verzoek tot opname vader of meemoeder bij doodgeboorte";
        };
    }

    String dateToLabel(LocalDateTime date) {
        var datum = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var tijd = date.format(DateTimeFormatter.ofPattern("HH:mm"));
        return "Opgeladen op " + datum + " om " + tijd;
    }

}
