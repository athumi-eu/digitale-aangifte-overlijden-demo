package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration.RestClientProvider;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.PageableResultJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.VerslagParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@SessionAttributes("kbonummer")
public class VerslagDao {

    private final RestClientProvider webclientProvider;
    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    public VerslagDao(RestClientProvider webclientProvider) {
        this.webclientProvider = webclientProvider;
    }

    @GetMapping(value = "/verslagen")
    public String dossier(Model model, @RequestParam String kbonummer, @RequestParam(required = false) List<String> postcode) {
        StringBuilder url = new StringBuilder(daoServiceUrl + "/burgerlijke-stand/v2/verslagen-beedigd-arts?kbonummer=" + kbonummer);
        if (postcode != null) {
            for (String code : postcode) {
                url.append("&postcode=").append(code);
            }
        }
        PageableResultJSON<VerslagBeedigdArtsJSON> response = webclientProvider.getRestClient(kbonummer)
                .get()
                .uri(url.toString())
                .retrieve()
                .body(new ParameterizedTypeReference<PageableResultJSON<VerslagBeedigdArtsJSON>>() {});

        if (!Objects.isNull(response)) {
            List<VerslagBeedigdArtsJSON> verslagen = new ArrayList<>(response.getElementen());
            model.addAttribute("verslagen", verslagen.stream().map(VerslagParser::new).toList());
        }
        model.addAttribute("kbonummer", kbonummer);

        return "ongekoppelde-verslagen";
    }

    @GetMapping(value = "/verslag")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        Optional<VerslagBeedigdArtsJSON> detail = Optional.ofNullable(webclientProvider.getRestClient(kbonummer)
                        .get()
                        .uri(daoServiceUrl + "/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}", id)
                        .retrieve()
                        .body(VerslagBeedigdArtsJSON.class));

        model.addAttribute("kbonummer", kbonummer);
        model.addAttribute("id", id);

        if (detail.isPresent()) {
            var teParsen = detail.get();
            model.addAttribute("verslagDetail", new VerslagParser(teParsen));

            return "ongekoppeld-verslag-page";
        }
        return "detail-does-not-exist";
    }

    @PostMapping(path = "/verslag/{id}/koppel")
    @ResponseBody
    public ResponseEntity<String> koppelVerslag(@PathVariable String id, @RequestParam String kbonummer, @RequestBody String dossierNummer) {
        try {
            webclientProvider.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/koppel", id)
                    .body(new DossierNummer(dossierNummer))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/verslag/{id}/ontkoppel")
    @ResponseBody
    public ResponseEntity<String> ontkoppelVerslag(@PathVariable String id, @SessionAttribute String kbonummer) {
        try {
            webclientProvider.getRestClient(kbonummer)
                    .post()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/ontkoppel", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Hello World!");
    }

    @DeleteMapping(path = "/verslag/{id}")
    @ResponseBody
    public ResponseEntity<String> verwijderVerslag(@PathVariable String id, @SessionAttribute String kbonummer) {
        try {
            webclientProvider.getRestClient(kbonummer)
                    .delete()
                    .uri(daoServiceUrl + "/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("Hello World!");
    }

    public record DossierNummer(String dossierNummer) {
    }

}
