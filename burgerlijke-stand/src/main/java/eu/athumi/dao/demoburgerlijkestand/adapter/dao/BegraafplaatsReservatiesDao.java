package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration.RestClientProvider;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats.ReservatieBegraafplaatsLokaalBestuurJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats.ReservatieFeedbackJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats.ReservatiesJSON;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static java.time.ZonedDateTime.of;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.util.Optional.ofNullable;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Controller
@SessionAttributes("kbonummer")
public class BegraafplaatsReservatiesDao {

    private final RestClientProvider securedWebClient;
    private final ObjectMapper objectMapper;

    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    public BegraafplaatsReservatiesDao(RestClientProvider securedWebClient, ObjectMapper objectMapper) {
        this.securedWebClient = securedWebClient;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/reservaties-begraafplaats")
    public String reservaties(
            Model model,
            HttpSession session,
            @RequestParam(required = false) String kbonummer,
            @RequestParam(required = false) String rijksregisternummer,
            @RequestParam(required = false) LocalDate van,
            @RequestParam(required = false) LocalDate tot,
            @RequestParam(required = false) String dossiernummer
    ) {
        model.addAttribute("kbonummer", kbonummer);
        session.setAttribute("kbonummer", kbonummer);

        if (rijksregisternummer == null && van == null && dossiernummer == null) {
            model.addAttribute("reservaties", List.of());
            return "reservaties-begraafplaats";
        }

        String query = new DefaultUriBuilderFactory().builder()
                .queryParamIfPresent("registryNumber", ofNullable(rijksregisternummer))
                .queryParamIfPresent("caseNumber", ofNullable(dossiernummer))
                .queryParamIfPresent("from", ofNullable(van).map(d -> of(d.atStartOfDay(), ZoneId.systemDefault()).format(ISO_INSTANT)))
                .queryParamIfPresent("until", ofNullable(tot).map(d -> of(d.atStartOfDay(), ZoneId.systemDefault()).format(ISO_INSTANT)))
                .build().getQuery();

        String url = daoServiceUrl + "/begraafplaats/v1/reservations" + (query != null ? "?" + query : "");

        try {
            String rawResponse = securedWebClient.getRestClient(kbonummer)
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            List<ReservatieBegraafplaatsLokaalBestuurJSON> reservaties;
            if (rawResponse == null || rawResponse.isBlank()) {
                reservaties = List.of();
            } else if (rawResponse.trim().startsWith("[")) {
                reservaties = objectMapper.readValue(rawResponse,
                        new TypeReference<List<ReservatieBegraafplaatsLokaalBestuurJSON>>() {});
            } else {
                reservaties = objectMapper.readValue(rawResponse,
                        ReservatiesJSON.class).reservations();
            }
            model.addAttribute("reservaties", reservaties);
        } catch (HttpClientErrorException ex) {
            model.addAttribute("reservaties", List.of());
            model.addAttribute("fout", ex.getResponseBodyAsString());
        } catch (Exception ex) {
            model.addAttribute("reservaties", List.of());
            model.addAttribute("fout", ex.getMessage());
        }

        return "reservaties-begraafplaats";
    }

    @GetMapping(value = "/reservatie-begraafplaats")
    public String reservatieDetail(
            Model model,
            @RequestParam String dossiernummer,
            @RequestParam String kbonummer
    ) {
        model.addAttribute("kbonummer", kbonummer);
        model.addAttribute("dossiernummer", dossiernummer);

        String url = daoServiceUrl + "/begraafplaats/v1/reservations?caseNumber=" + dossiernummer;

        try {
            String rawResponse = securedWebClient.getRestClient(kbonummer)
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            if (rawResponse != null && !rawResponse.isBlank()) {
                ReservatieBegraafplaatsLokaalBestuurJSON reservatie;
                if (rawResponse.trim().startsWith("[")) {
                    List<ReservatieBegraafplaatsLokaalBestuurJSON> list = objectMapper.readValue(rawResponse,
                            new TypeReference<>() {});
                    reservatie = list.isEmpty() ? null : list.getFirst();
                } else {
                    reservatie = objectMapper.readValue(rawResponse,
                            ReservatiesJSON.class).reservations().stream().findFirst().orElse(null);
                }
                model.addAttribute("reservatie", reservatie);
            }
        } catch (HttpClientErrorException ex) {
            model.addAttribute("fout", ex.getResponseBodyAsString());
        } catch (Exception ex) {
            model.addAttribute("fout", ex.getMessage());
        }

        return "reservatie-begraafplaats-detail";
    }

    @PutMapping(path = "/reservatie-begraafplaats/{dossiernummer}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> beoordeelReservatie(
            @PathVariable String dossiernummer,
            @RequestBody ReservatieFeedbackJSON feedback,
            @SessionAttribute String kbonummer
    ) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .put()
                    .uri(daoServiceUrl + "/begraafplaats/v1/reservations/{dossiernummer}/status", dossiernummer)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(feedback)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.badRequest().body(ex.getResponseBodyAsString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/reservatie-begraafplaats/{dossiernummer}/rustplaats-details")
    @ResponseBody
    public ResponseEntity<String> uploadRustplaatsDetails(
            @PathVariable String dossiernummer,
            @RequestParam MultipartFile akte,
            @SessionAttribute String kbonummer
    ) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("akte", akte.getResource());
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/begraafplaats/v1/reservations/{dossiernummer}/restingplacedetails", dossiernummer)
                    .contentType(MULTIPART_FORM_DATA)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.badRequest().body(ex.getResponseBodyAsString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @GetMapping(value = "/reservatie-begraafplaats/{dossiernummer}/rustplaats-details")
    @ResponseBody
    public ResponseEntity<byte[]> downloadRustplaatsDetails(
            @PathVariable String dossiernummer,
            @SessionAttribute String kbonummer
    ) {
        ResponseEntity<byte[]> entity = securedWebClient.getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/begraafplaats/v1/reservations/{dossiernummer}/restingplacedetails", dossiernummer)
                .retrieve()
                .toEntity(byte[].class);
        return ResponseEntity.ok()
                .contentType(entity.getHeaders().getContentType() != null
                        ? entity.getHeaders().getContentType()
                        : MediaType.APPLICATION_OCTET_STREAM)
                .body(entity.getBody());
    }

    @DeleteMapping(path = "/reservatie-begraafplaats/{dossiernummer}/rustplaats-details")
    @ResponseBody
    public ResponseEntity<String> verwijderRustplaatsDetails(
            @PathVariable String dossiernummer,
            @SessionAttribute String kbonummer
    ) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .delete()
                    .uri(daoServiceUrl + "/begraafplaats/v1/reservations/{dossiernummer}/restingplacedetails", dossiernummer)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.badRequest().body(ex.getResponseBodyAsString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }
}
