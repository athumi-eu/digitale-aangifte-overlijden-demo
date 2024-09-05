package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.VerslagParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        List<VerslagBeedigdArtsJSON> verslagen = new ArrayList<>();
        if(!Objects.isNull(response.getBody())) {
            verslagen.addAll(Arrays.stream(response.getBody()).toList());
            model.addAttribute("verslagen", verslagen.stream().map(VerslagParser::new).toList());
        }
        model.addAttribute("kbonummer", kbonummer);

        return verslagen.isEmpty() ? "niks-gevonden" : "verslagen";
    }

    @GetMapping(value = "/verslag")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        String url = UriComponentsBuilder.fromHttpUrl(daoServiceUrl + "/burgerlijke-stand/verslagen-beedigd-arts").queryParam("kbonummer", kbonummer).build().toString();
        var detail = Arrays.stream(Objects.requireNonNull(oauth2RestTemplate.getForEntity(url, VerslagBeedigdArtsJSON[].class).getBody())).filter(verslag -> Objects.equals(verslag.id(), id)).findFirst();

        model.addAttribute("kbonummer", kbonummer);

        if (detail.isPresent()) {
            var teParsen = detail.get();
                model.addAttribute("verslagDetail", new VerslagParser(teParsen));
                return "verslag-page";
        }
        return "detail-does-not-exist";
    }

}
