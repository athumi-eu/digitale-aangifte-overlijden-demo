package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration.RestClientProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Controller
public class FileDao {

    private final RestClientProvider securedWebClient;
    private final String daoServiceUrl;

    public FileDao(RestClientProvider securedWebClient, @Value("${dao.service.connection-url}") String daoServiceUrl) {
        this.securedWebClient = securedWebClient;
        this.daoServiceUrl = daoServiceUrl;
    }

    @PostMapping("/aktes/upload")
    @ResponseBody
    public void uploadAkte( @RequestParam(value = "akte", required = false) MultipartFile file, @RequestParam(required = false) String aktenummer, @RequestParam String type, @RequestParam String id, @SessionAttribute String kbonummer) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        if(file.getSize() > 0){
            body.add("akte", file.getResource());
        }
        Optional.ofNullable(aktenummer).ifPresent(item -> body.add("aktenummer", aktenummer));
        securedWebClient
                .getRestClient(kbonummer)
                .post()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/aktes/{type}", id, type)
                .body(body)
                .contentType(MULTIPART_FORM_DATA)
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping(value = "/documenten")
    @ResponseBody
    public ResponseEntity<byte[]> downloadDocument(@RequestParam("dossierId") String dossierId, @RequestParam("type") String type, @SessionAttribute String kbonummer) {
        ResponseEntity<byte[]> entity = securedWebClient
                .getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/documenten/{type}", dossierId, type)
                .retrieve()
                .toEntity(byte[].class);
        return ResponseEntity.ok()
                .contentType(Objects.requireNonNull(entity.getHeaders().getContentType()))
                .body(entity.getBody());
    }

    @GetMapping(value = "/aktes/download", produces = "application/pdf")
    @ResponseBody
    public byte[] downloadAkte(@RequestParam String type, @RequestParam String id, @SessionAttribute String kbonummer) {
        return securedWebClient
                .getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/aktes/{type}", id, type)
                .retrieve()
                .toEntity(byte[].class).getBody();
    }

    @PostMapping("/toestemming/upload")
    @ResponseBody
    public void uploadFile(@RequestParam MultipartFile toestemming, @RequestParam LocalDateTime aanmaakDatumToestemming, @RequestParam String id, @SessionAttribute String kbonummer) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("toestemming", toestemming.getResource());
        body.add("aanmaakDatumToestemming", ZonedDateTime.of(aanmaakDatumToestemming, ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME));
        securedWebClient
                .getRestClient(kbonummer)
                .post()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/toestemming", id)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping(value = "/toestemming/download", produces = "application/pdf")
    @ResponseBody
    public byte[] downloadFile(@RequestParam String id, @SessionAttribute String kbonummer) {
        return securedWebClient
                .getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossierId}/toestemming", id)
                .retrieve()
                .toEntity(byte[].class).getBody();
    }

    private enum DocumentType {
        VERZOEK_NABESTAANDE,
        TOESTEMMING_EIGENAAR,
        VERZOEK_NABESTAANDE_AS_PARTNER,
        VERZOEK_OPNAME_ALS_VADER_OF_MEEMOEDER,
        NATIONALE_AKTE,
        INTERNATIONALE_AKTE,
        TOESTEMMING_BEGRAFENIS_OF_CREMATIE,
    }
}
