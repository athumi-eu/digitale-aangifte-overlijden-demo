package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("kbonummer")
public class ArtsDao {

    @GetMapping(value = "/arts")
    public String dossier(Model model, @RequestParam String kbonummer, @RequestParam(required = false) List<String> postcode) {
        model.addAttribute("kbonummer", kbonummer);
        return "arts-login";
    }

}
