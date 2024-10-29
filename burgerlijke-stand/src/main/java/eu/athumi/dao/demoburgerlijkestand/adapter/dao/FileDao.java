package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
public class FileDao {

    private final RestClient securedWebClient;
    private final String daoServiceUrl;

    public FileDao(RestClient securedWebClient, @Value("${dao.service.connection-url}") String daoServiceUrl) {
        this.securedWebClient = securedWebClient;
        this.daoServiceUrl = daoServiceUrl;
    }

    @PostMapping("/aktes/upload")
    @ResponseBody
    public void uploadAkte(@RequestParam("akte") MultipartFile file, @RequestParam String type, @RequestParam String id) {
        MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
        body.add("akte", file.getResource());
        securedWebClient
                .post()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/aktes/{type}", id, type)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping(value = "/documenten/", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] downloadDocument(@RequestParam("dossierId") String dossierId, @RequestParam("type") String type) {
        //TODO dit endpoint bestaat nog niet. Te reviseren na finaliseren documenten module.
        return securedWebClient
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/documenten/{type}", dossierId, type)
                .retrieve()
                .toEntity(byte[].class).getBody();
    }

    @GetMapping(value = "/aktes/download", produces = "application/pdf")
    @ResponseBody
    public byte[] downloadAkte(@RequestParam String type, @RequestParam String id) {
        DocumentType docType;
        if (Objects.equals("nationaal", type)) {
            docType = DocumentType.NATIONALE_AKTE;
        } else {
            docType = DocumentType.INTERNATIONALE_AKTE;
        }

        return securedWebClient
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/documenten/{type}", id, docType.name())
                .retrieve()
                .toEntity(byte[].class).getBody();
    }

    @PostMapping("/toestemming/upload")
    @ResponseBody
    public void uploadFile(@RequestParam MultipartFile toestemming, @RequestParam LocalDateTime aanmaakDatumToestemming, @RequestParam String id) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("toestemming", toestemming.getResource());
        body.add("aanmaakDatumToestemming", aanmaakDatumToestemming.format(DateTimeFormatter.ISO_DATE_TIME));
        securedWebClient
                .post()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/toestemming", id)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping(value = "/toestemming/download", produces = "application/pdf")
    @ResponseBody
    public byte[] downloadFile(@RequestParam String id) {
        return securedWebClient
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/documenten/{type}", id, DocumentType.TOESTEMMING_VERWERKING_OVERLIJDEN.name())
                .retrieve()
                .toEntity(byte[].class).getBody();
    }

    private enum DocumentType {
        // TODO: DAO-112 betere naamgeving
        VERZOEK_NABESTAANDE,
        TOESTEMMING_EIGENAAR,
        VERZOEK_NABESTAANDE_AS_PARTNER,
        VERZOEK_OPNAME_ALS_VADER_OF_MEEMOEDER,
        NATIONALE_AKTE,
        INTERNATIONALE_AKTE,
        TOESTEMMING_VERWERKING_OVERLIJDEN,
    }
}
