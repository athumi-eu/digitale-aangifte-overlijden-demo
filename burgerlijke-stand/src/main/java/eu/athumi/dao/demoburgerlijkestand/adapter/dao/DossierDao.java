package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.VaststellingType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.DossierVerrijkingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.JongerDanEenJaarParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.OuderDanEenJaarParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.VerslagParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Controller
public class DossierDao {

    private final RestClient securedWebClient;
    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    private ObjectMapper objectMapper;

    public DossierDao(RestClient securedWebClient, ObjectMapper objectMapper) {
        this.securedWebClient = securedWebClient;
        this.objectMapper = objectMapper;
    }

    @GetMapping()
    public String index() {
        return "layout";
    }


    @GetMapping(value = "/dossiers")
    public String dossierDetailFilter(Model model,
                                      @RequestParam String kbonummer,
                                      @RequestParam(required = false) String status,
                                      @RequestParam(required = false) String dossiernummer,
                                      @RequestParam(required = false) String rijksregisternummer,
                                      @RequestParam(required = false) LocalDate overlijden,
                                      @RequestParam(required = false) String achternaam,
                                      @RequestParam(required = false) Boolean heeftVerslagBeedigdArts,
                                      @RequestParam(required = false) Boolean heeftNationaleOverlijdensakte,
                                      @RequestParam(required = false) Boolean heeftInlichtingenfiche,
                                      @RequestParam(required = false) Boolean heeftToestemming
    ) {

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
            DossierBurgerlijkeStandJSON[] response = securedWebClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(DossierBurgerlijkeStandJSON[].class);
            model.addAttribute("dossiers", response);
            model.addAttribute("kbonummer", kbonummer);
            return "dossiers";
        } catch (HttpClientErrorException ex) {
            if (ex.getMessage().contains("Gelieve een extra request parameter toe te voegen")) {
                model.addAttribute("NOT_ENOUGH_PARAMS", true);
                model.addAttribute("dossiers", List.of());
                model.addAttribute("kbonummer", kbonummer);
                return "dossiers";
            } else {
                throw ex;
            }
        }

    }


    @GetMapping(value = "/dossier")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        Optional<DossierBurgerlijkeStandJSON> detail = Arrays.stream(securedWebClient
                        .get()
                        .uri(daoServiceUrl + "/burgerlijke-stand/v1/dossiers?kbonummer={kbonummer}&dossiernummer={id}", kbonummer, id)
                        .retrieve()
                        .body(DossierBurgerlijkeStandJSON[].class))
                .filter(dossier -> Objects.equals(dossier.id(), id))
                .findFirst();

        model.addAttribute("kbonummer", kbonummer);

        System.out.println(detail.get());
        if (detail.isPresent()) {
            var dossier = detail.get();
            var verslag = ofNullable(dossier.verslagDetailURL()).map(this::getVerslagDetail).map(VerslagParser::new).orElse(null);
            if (Objects.equals(VaststellingType.OVERLIJDEN_PERSOON_OUDER_DAN_1_JAAR, dossier.vaststellingType())) {
                model.addAttribute("dossier", dossier);
                model.addAttribute("verslag", verslag);
                model.addAttribute("parsedDetail", new OuderDanEenJaarParser(dossier));
                return "detail-ouder-dan-1-jaar";
            } else {
                model.addAttribute("dossier", dossier);
                model.addAttribute("verslag", verslag);
                model.addAttribute("parsedDetail", new JongerDanEenJaarParser(dossier));
                return "detail-jonger-dan-1-jaar";
            }

        }
        return "detail-does-not-exist";
    }

    public VerslagBeedigdArtsJSON getVerslagDetail(URI verslagDetailURL) {

        VerslagBeedigdArtsJSON body = securedWebClient
                .get()
                .uri(verslagDetailURL)
                .retrieve()
                .body(VerslagBeedigdArtsJSON.class);
        return body;
    }

    @PostMapping(path = "/dossier/{id}/afsluiten")
    @ResponseBody
    public ResponseEntity<String> afsluitenDossier(@PathVariable String id) {
        try {
            securedWebClient
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
    public ResponseEntity<String> heropenDossier(@PathVariable String id) {
        try {
            securedWebClient
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
    public ResponseEntity<String> verrijkDossier(@PathVariable String id, @RequestBody String verrijking) {
        try {
            securedWebClient
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
}
