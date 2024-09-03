package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.VaststellingType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.JongerDanEenJaarParser;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.OuderDanEenJaarParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;

@Controller
public class DossierDao {

    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    private final RestTemplate restTemplate;

    public DossierDao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public String index() {
        return "layout";
    }

    @GetMapping(value = "/dossiers")
    public String dossier(Model model, @RequestParam String kbonummer) {
        String url = UriComponentsBuilder.fromHttpUrl(daoServiceUrl + "/dossiers-burgerlijke-stand").queryParam("kbonummer", kbonummer).build().toString();


        ResponseEntity<DossierBurgerlijkeStandJSON[]> response = restTemplate.getForEntity(url, DossierBurgerlijkeStandJSON[].class);
        model.addAttribute("dossiers", response.getBody());
        model.addAttribute("kbonummer", kbonummer);

        return Objects.isNull(response.getBody()) || response.getBody().length == 0 ? "geen-dossiers" : "dossiers";
    }

    @GetMapping(value = "/dossier")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        String url = UriComponentsBuilder.fromHttpUrl(daoServiceUrl + "/dossiers-burgerlijke-stand").queryParam("kbonummer", kbonummer).build().toString();
        var detail = Arrays.stream(Objects.requireNonNull(restTemplate.getForEntity(url, DossierBurgerlijkeStandJSON[].class).getBody())).filter(dossier -> Objects.equals(dossier.id(), id)).findFirst();


        System.out.println("htmx called");
        model.addAttribute("kbonummer", kbonummer);

        if (detail.isPresent()) {
            var dossier = detail.get();
            if (Objects.equals(VaststellingType.OVERLIJDEN_PERSOON_OUDER_DAN_1_JAAR, dossier.vaststellingType())) {
                model.addAttribute("detail", dossier);
                model.addAttribute("dossier", new OuderDanEenJaarParser(dossier));
                return "detail-ouder-dan-1-jaar";
            } else {
                model.addAttribute("detail", dossier);
                model.addAttribute("dossier", new JongerDanEenJaarParser(dossier));
                return "detail-jonger-dan-1-jaar";
            }

        }
        return "detail-does-not-exist";
    }

}
