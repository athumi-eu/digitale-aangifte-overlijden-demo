package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Controller
public class VerslagDao {

    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    private final RestTemplate oauth2RestTemplate;

    public VerslagDao(RestTemplate oauth2RestTemplate) {
        this.oauth2RestTemplate = oauth2RestTemplate;
    }

    @GetMapping(value = "/verslagen")
    public String dossier(Model model, @RequestParam String kbonummer) {

        String url = UriComponentsBuilder.fromHttpUrl(daoServiceUrl + "/burgerlijke-stand/verslagen-beedigd-arts").queryParam("kbonummer", kbonummer).build().toString();
        ResponseEntity<VerslagBeedigdArtsJSON[]> response = oauth2RestTemplate.getForEntity(url, VerslagBeedigdArtsJSON[].class);

        model.addAttribute("verslagen", response.getBody());
        model.addAttribute("kbonummer", kbonummer);

        return Objects.isNull(response.getBody()) || response.getBody().length == 0 ? "niks-gevonden" : "verslagen";
    }

}
