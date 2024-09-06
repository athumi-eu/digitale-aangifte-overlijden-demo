package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.VerslagBeedigdArtsJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.VerslagParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.*;

@Controller
public class VerslagDao {

    private final RestClient securedWebClient;
    @Value("${dao.service.connection-url}")
    private String daoServiceUrl;

    public VerslagDao(RestClient securedWebClient) {
        this.securedWebClient = securedWebClient;
    }

    @GetMapping(value = "/verslagen")
    public String dossier(Model model, @RequestParam String kbonummer) {
        VerslagBeedigdArtsJSON[] response = securedWebClient
            .get()
            .uri(daoServiceUrl + "/burgerlijke-stand/verslagen-beedigd-arts?kbonummer={kbonummer}", kbonummer)
            .retrieve()
            .body(VerslagBeedigdArtsJSON[].class);

        List<VerslagBeedigdArtsJSON> verslagen = new ArrayList<>();
        if (!Objects.isNull(response)) {
            verslagen.addAll(Arrays.stream(response).toList());
            model.addAttribute("verslagen", verslagen.stream().map(VerslagParser::new).toList());
        }
        model.addAttribute("kbonummer", kbonummer);

        return verslagen.isEmpty() ? "niks-gevonden" : "verslagen";
    }

    @GetMapping(value = "/verslag")
    public String dossierDetail(Model model, @RequestParam String id, @RequestParam String kbonummer) {
        Optional<VerslagBeedigdArtsJSON> detail = Arrays.stream(securedWebClient
                .get()
                .uri(daoServiceUrl + "/burgerlijke-stand/verslagen-beedigd-arts?kbonummer={kbonummer}", kbonummer)
                .retrieve()
                .body(VerslagBeedigdArtsJSON[].class))
            .filter(dossier -> Objects.equals(dossier.id(), id))
            .findFirst();

        model.addAttribute("kbonummer", kbonummer);

        if (detail.isPresent()) {
            var teParsen = detail.get();
            model.addAttribute("verslagDetail", new VerslagParser(teParsen));
            return "verslag-page";
        }
        return "detail-does-not-exist";
    }

}
