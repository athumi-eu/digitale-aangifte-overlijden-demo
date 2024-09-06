package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.VaststellingType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.JongerDanEenJaarParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.OuderDanEenJaarParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Controller
public class DossierDao {

    private final RestClient securedWebClient;
    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    public DossierDao(RestClient securedWebClient) {
        this.securedWebClient = securedWebClient;
    }

    @GetMapping()
    public String index() {
        return "layout";
    }

    @GetMapping(value = "/dossiers")
    public String dossier(Model model, @RequestParam String kbonummer) {
        DossierBurgerlijkeStandJSON[] response = securedWebClient
            .get()
            .uri(daoServiceUrl + "/burgerlijke-stand/dossiers?kbonummer={kbonummer}", kbonummer)
            .retrieve()
            .body(DossierBurgerlijkeStandJSON[].class);

        model.addAttribute("dossiers", response);
        model.addAttribute("kbonummer", kbonummer);

        return Objects.isNull(response) || response.length == 0 ? "niks-gevonden" : "dossiers";
    }

    @GetMapping(value = "/dossier")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        Optional<DossierBurgerlijkeStandJSON> detail = Arrays.stream(securedWebClient
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/dossiers?kbonummer={kbonummer}", kbonummer)
                .retrieve()
                .body(DossierBurgerlijkeStandJSON[].class))
            .filter(dossier -> Objects.equals(dossier.id(), id))
            .findFirst();

        model.addAttribute("kbonummer", kbonummer);

        if (detail.isPresent()) {
            var dossier = detail.get();
            if (Objects.equals(VaststellingType.OVERLIJDEN_PERSOON_OUDER_DAN_1_JAAR, dossier.vaststellingType())) {
                model.addAttribute("dossier", dossier);
                model.addAttribute("verslag", null);
                model.addAttribute("parsedDetail", new OuderDanEenJaarParser(dossier));
                return "detail-ouder-dan-1-jaar";
            } else {
                model.addAttribute("dossier", dossier);
                model.addAttribute("verslag", null);
                model.addAttribute("parsedDetail", new JongerDanEenJaarParser(dossier));
                return "detail-jonger-dan-1-jaar";
            }

        }
        return "detail-does-not-exist";
    }

}
