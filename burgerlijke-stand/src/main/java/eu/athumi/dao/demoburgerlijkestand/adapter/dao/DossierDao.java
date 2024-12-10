package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration.RestClientProvider;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.VaststellingType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische.SEGLB;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.DossierVerrijkingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.FicheDocumentenParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.JongerDanEenJaarParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.OuderDanEenJaarParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.VerslagParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.StatistischeGegevensParserOuderDanEenJaar;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar.StatistischeGegevensParserJongerDanEenJaar;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
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
                                      HttpEntity<Object> httpEntity) {

        String url = daoServiceUrl + "/burgerlijke-stand/v1/dossiers?" + new DefaultUriBuilderFactory().builder().queryParam("kbonummer", kbonummer)
                .queryParamIfPresent("status", ofNullable(status))
                .queryParamIfPresent("dossiernummer", ofNullable(dossiernummer))
                .queryParamIfPresent("rijksregisternummer", ofNullable(rijksregisternummer))
                .queryParamIfPresent("overlijden", ofNullable(overlijden))
                .queryParamIfPresent("achternaam", ofNullable(achternaam))
                .queryParamIfPresent("heeftVerslagBeedigdArts", ofNullable(heeftVerslagBeedigdArts))
                .queryParamIfPresent("heeftNationaleOverlijdensakte", ofNullable(heeftNationaleOverlijdensakte))
                .queryParamIfPresent("heeftInlichtingenfiche", ofNullable(heeftInlichtingenfiche))
                .queryParamIfPresent("heeftToestemming", ofNullable(heeftToestemming))
                .build().getQuery();

        try {
            DossierBurgerlijkeStandJSON[] response = securedWebClient.getRestClient(kbonummer)
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(DossierBurgerlijkeStandJSON[].class);
            model.addAttribute("dossiers", response);
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
        Optional<DossierBurgerlijkeStandJSON> detail = Arrays.stream(securedWebClient.getRestClient(kbonummer)
                        .get()
                        .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers?kbonummer={kbonummer}&dossiernummer={id}", kbonummer, id)
                        .retrieve()
                        .body(DossierBurgerlijkeStandJSON[].class))
                .filter(dossier -> Objects.equals(dossier.id(), id))
                .findFirst();

        model.addAttribute("kbonummer", kbonummer);

        if (detail.isPresent()) {
            var dossier = detail.get();
            var verslag = ofNullable(dossier.verslagDetailURL()).map((URI verslagDetailURL) -> getVerslagDetail(verslagDetailURL, kbonummer)).map(VerslagParser::new).orElse(null);
            var statistischeGegevens = getStatistischeGegevens(kbonummer, dossier.id());

            model.addAttribute("ficheDocumenten", new FicheDocumentenParser(dossier));
            if (Objects.equals(VaststellingType.OVERLIJDEN_PERSOON_OUDER_DAN_1_JAAR, dossier.vaststellingType())) {
                model.addAttribute("dossier", dossier);
                model.addAttribute("verslag", verslag);
                model.addAttribute("statistischeGegevens", new StatistischeGegevensParserOuderDanEenJaar(statistischeGegevens));
                model.addAttribute("parsedDetail", new OuderDanEenJaarParser(dossier));
                return "detail-ouder-dan-1-jaar";
            } else {
                model.addAttribute("dossier", dossier);
                model.addAttribute("verslag", verslag);
                model.addAttribute("statistischeGegevens", new StatistischeGegevensParserJongerDanEenJaar(statistischeGegevens));
                model.addAttribute("parsedDetail", new JongerDanEenJaarParser(dossier));
                return "detail-jonger-dan-1-jaar";
            }

        }
        return "detail-does-not-exist";
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


    @PostMapping(path = "/dossier/{id}/heropen")
    @ResponseBody
    public ResponseEntity<String> heropenDossier(@PathVariable String id, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/heropen", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/dossier/{id}/verrijk")
    @ResponseBody
    public ResponseEntity<String> verrijkDossier(@PathVariable String id, @RequestBody String verrijking, @SessionAttribute String kbonummer) {
        try {
            securedWebClient.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers/{id}/verrijken", id)
                    .body(objectMapper.readValue(
                            verrijking,
                            DossierVerrijkingJSON.class
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

}
