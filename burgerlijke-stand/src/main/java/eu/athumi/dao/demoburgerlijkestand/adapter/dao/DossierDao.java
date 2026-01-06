package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration.RestClientProvider;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.PageableResultJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.VaststellingType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.aanvulling.DossierAanvullingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.laatsteWilsbeschikking.LaatsteWilsbeschikkingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische.SEGLB;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.*;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.StatistischeGegevensParserOuderDanEenJaar;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar.StatistischeGegevensParserJongerDanEenJaar;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Controller
@SessionAttributes("kbonummer")
public class DossierDao {

    private final RestClientProvider securedWebClient;
    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    private ObjectMapper objectMapper;

    public DossierDao(RestClientProvider securedWebClient, ObjectMapper objectMapper) {
        this.securedWebClient = securedWebClient;
        this.objectMapper = objectMapper;
    }

    @GetMapping()
    public String index() {
        return "layout";
    }


    @GetMapping(value = "/dossiers")
    public String dossierDetailFilter(Model model,
                                      HttpSession session,
                                      @RequestParam(required = false, defaultValue = "0207521503") String kbonummer,
                                      @RequestParam(required = false) String status,
                                      @RequestParam(required = false) String dossiernummer,
                                      @RequestParam(required = false) String rijksregisternummer,
                                      @RequestParam(required = false) LocalDate overlijden,
                                      @RequestParam(required = false) String achternaam,
                                      @RequestParam(required = false) Boolean heeftVerslagBeedigdArts,
                                      @RequestParam(required = false) Boolean heeftNationaleOverlijdensakte,
                                      @RequestParam(required = false) Boolean heeftInlichtingenfiche,
                                      @RequestParam(required = false) Boolean heeftToestemming,
                                      @RequestParam(required = false) String postcodes,
                                      HttpEntity<Object> httpEntity) {
        var postcode = ofNullable(postcodes).map(p -> p.split(",")).map(List::of).orElse(List.of());
        String url = daoServiceUrl + "/burgerlijke-stand/v2/dossiers?" + new DefaultUriBuilderFactory().builder().queryParam("kbonummer", kbonummer)
                .queryParamIfPresent("status", ofNullable(status))
                .queryParamIfPresent("dossiernummer", ofNullable(dossiernummer))
                .queryParamIfPresent("rijksregisternummer", ofNullable(rijksregisternummer))
                .queryParamIfPresent("overlijden", ofNullable(overlijden))
                .queryParamIfPresent("achternaam", ofNullable(achternaam))
                .queryParamIfPresent("heeftVerslagBeedigdArts", ofNullable(heeftVerslagBeedigdArts))
                .queryParamIfPresent("heeftNationaleOverlijdensakte", ofNullable(heeftNationaleOverlijdensakte))
                .queryParamIfPresent("heeftInlichtingenfiche", ofNullable(heeftInlichtingenfiche))
                .queryParamIfPresent("heeftToestemming", ofNullable(heeftToestemming))
                .queryParam("postcode", postcode)
                .build().getQuery();

        try {
            PageableResultJSON<DossierBurgerlijkeStandJSON> response = securedWebClient.getRestClient(kbonummer)
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<PageableResultJSON<DossierBurgerlijkeStandJSON>>() {
                    });
            model.addAttribute("dossiers", response.getElementen());
            model.addAttribute("kbonummer", kbonummer);
            session.setAttribute("kbonummer", kbonummer);
            return "dossiers";
        } catch (HttpClientErrorException ex) {
            if (ex.getMessage().contains("Gelieve een extra request parameter toe te voegen")) {
                model.addAttribute("NOT_ENOUGH_PARAMS", true);
                model.addAttribute("dossiers", List.of());
                model.addAttribute("kbonummer", kbonummer);
                return "dossiers";
            } else if (ex.getStatusCode().value() == 403) {
                model.addAttribute("dossiers", new DossierBurgerlijkeStandJSON[0]);
                model.addAttribute("kbonummer", kbonummer);
                return "dossiers";
            } else {
                throw ex;
            }
        }

    }


    @GetMapping(value = "/dossier")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        PageableResultJSON<DossierBurgerlijkeStandJSON> dossiers = securedWebClient.getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v2/dossiers?kbonummer={kbonummer}&dossiernummer={id}", kbonummer, id)
                .retrieve()
                .body(new ParameterizedTypeReference<PageableResultJSON<DossierBurgerlijkeStandJSON>>() {
                });

        assert dossiers != null;
        Optional<DossierBurgerlijkeStandJSON> detail = dossiers.getElementen().stream().filter(dossier -> Objects.equals(dossier.id(), id))
                .findFirst();

        model.addAttribute("kbonummer", kbonummer);

        if (detail.isPresent()) {
            var dossier = detail.get();
            var verslag = ofNullable(dossier.verslagDetailURL()).map((URI verslagDetailURL) -> getVerslagDetail(verslagDetailURL, kbonummer)).map(VerslagParser::new).orElse(null);
            var laatsteWilsbeschikking = ofNullable(dossier.laatsteWilsbeschikkingURI()).map((URI laatsteWilsbeschikkingURI) -> getLaatsteWilsbeschikking(laatsteWilsbeschikkingURI, kbonummer)).orElse(null);
            var statistischeGegevens = getStatistischeGegevens(kbonummer, dossier.id());

            model.addAttribute("ficheDocumenten", new FicheDocumentenParser(dossier));
            model.addAttribute("dossier", dossier);
            model.addAttribute("verslag", verslag);
            model.addAttribute("kbonummer", kbonummer);
            model.addAttribute("laatsteWilsbeschikking", laatsteWilsbeschikking);
            if (Objects.equals(VaststellingType.OVERLIJDEN_PERSOON_OUDER_DAN_1_JAAR, dossier.vaststellingType())) {
                model.addAttribute("statistischeGegevens", new StatistischeGegevensParserOuderDanEenJaar(statistischeGegevens));
                model.addAttribute("parsedDetail", new OuderDanEenJaarParser(dossier));

                return "detail-ouder-dan-1-jaar";
            } else {
                model.addAttribute("statistischeGegevens", new StatistischeGegevensParserJongerDanEenJaar(statistischeGegevens));
                model.addAttribute("parsedDetail", new JongerDanEenJaarParser(dossier));
                model.addAttribute("zwangerschapsduur", new MedischAttestZwangerschapsduurParser(dossier));
                return "detail-jonger-dan-1-jaar";
            }

        }
        return "detail-does-not-exist";
    }

    private LaatsteWilsbeschikkingJSON getLaatsteWilsbeschikking(URI laatsteWilsbeschikking, String kbonummer) {
        try {
            return securedWebClient.getRestClient(kbonummer)
                    .get()
                    .uri(laatsteWilsbeschikking)
                    .retrieve()
                    .body(LaatsteWilsbeschikkingJSON.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().value() == 410) {
                return null;
            }
            throw ex;
        }
    }

    public VerslagBeedigdArtsJSON getVerslagDetail(URI verslagDetailURL, String kbonummer) {

        VerslagBeedigdArtsJSON body = securedWebClient.getRestClient(kbonummer)
                .get()
                .uri(verslagDetailURL)
                .retrieve()
                .body(VerslagBeedigdArtsJSON.class);
        return body;
    }

    public StatistischeGegevensJSON getStatistischeGegevens(String kbonummer, String dossiernummer) {
        var tt = securedWebClient.getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossiernummer}/statistische-gegevens", dossiernummer)
                .retrieve()
                .body(String.class);
        return securedWebClient.getRestClient(kbonummer)
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{dossiernummer}/statistische-gegevens", dossiernummer)
                .retrieve()
                .body(StatistischeGegevensJSON.class);
    }

    @PostMapping(path = "/dossier/{id}/afsluiten")
    @ResponseBody
    public ResponseEntity<String> afsluitenDossier(@PathVariable String id, HttpSession session) {
        try {
            securedWebClient.getRestClient(session.getAttribute("kbonummer").toString())
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/afsluiten", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/dossier/{id}/verwijderen")
    @ResponseBody
    public ResponseEntity<String> verwijderenDossier(@PathVariable String id, HttpSession session, @RequestBody(required = false) @Nullable String message) {
        try {
            securedWebClient.getRestClient(session.getAttribute("kbonummer").toString())
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/verwijderen", id)
                    .body(new Message(message == null ? "" : message))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }


    @PostMapping(path = "/dossier/{id}/heropen")
    @ResponseBody
    public ResponseEntity<String> heropenDossier(@PathVariable String id, @SessionAttribute String kbonummer, @RequestBody(required = false) @Nullable String message) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/heropen", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new Message(message == null ? "" : message)).retrieve().toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }


    @PostMapping(path = "/dossier/{id}/aanvullen")
    @ResponseBody
    public ResponseEntity<String> verrijkDossier(@PathVariable String id, @RequestBody String verrijking, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/aanvullen", id)
                    .body(objectMapper.readValue(
                            verrijking,
                            DossierAanvullingJSON.class
                    ))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/dossier/{id}/seg")
    @ResponseBody
    public ResponseEntity<String> saveSEG(@PathVariable String id, @RequestBody String seg, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .put()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens/socio-economische-gegevens", id)
                    .body(objectMapper.readValue(
                            seg,
                            SEGLB.class
                    ))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/dossier/{id}/statistische-gegevens/refresh")
    @ResponseBody
    public ResponseEntity<String> refreshRijksregister(@PathVariable String id, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens/refresh", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/dossier/{id}/wijzig-plaats-overlijden")
    @ResponseBody
    public ResponseEntity<String> wijzigPlaatsOverlijden(@PathVariable String id, @RequestBody WijzigPlaatsOverlijden wijzigPlaatsOverlijden, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/wijzig-plaats-overlijden", id)
                    .body(wijzigPlaatsOverlijden)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    public record WijzigPlaatsOverlijden(String niscode, String postcode, String reden) {
    }

    @PostMapping(path = "/dossier/{id}/ontkoppel", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> ontkoppelUitvaartOndernemer(@PathVariable String id, @RequestBody @Nullable String message, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/ontkoppel", id)
                    .body("{\"boodschap\": \"" + Optional.ofNullable(message).orElse("") + "\"}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    public static record Message(String boodschap) {
    }

    ;
}
