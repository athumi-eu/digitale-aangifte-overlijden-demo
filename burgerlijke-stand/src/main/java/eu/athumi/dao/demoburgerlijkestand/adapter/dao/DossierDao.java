package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.DossierBurgerlijkeStandJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class DossierDao {

    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    private final RestTemplate restTemplate;

    public DossierDao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/dossiers")
    public String index(Model model, @RequestParam String kbonummer) {
        String url = UriComponentsBuilder.fromHttpUrl(daoServiceUrl + "/dossiers-burgerlijke-stand").queryParam("kbonummer", kbonummer).build().toString();


        ResponseEntity<DossierBurgerlijkeStandJSON[]> response = restTemplate.getForEntity(url, DossierBurgerlijkeStandJSON[].class);
        model.addAttribute("dossiers", response.getBody());

        return "dossiers";
    }
}
