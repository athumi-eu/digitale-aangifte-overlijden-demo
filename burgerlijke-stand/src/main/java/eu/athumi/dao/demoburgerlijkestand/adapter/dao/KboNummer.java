package eu.athumi.dao.demoburgerlijkestand.adapter.dao;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration.ClientConfigurationProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.List;

@ControllerAdvice
public class KboNummer {

    private final ClientConfigurationProperties clientConfigurationProperties;

    public KboNummer(ClientConfigurationProperties clientConfigurationProperties) {
        this.clientConfigurationProperties = clientConfigurationProperties;
    }

    @ModelAttribute("allGemeentes")
    public List<Gemeente> populateKboNummerList(Model model) {
        return clientConfigurationProperties.getGemeentes()
                .stream()
                .map(item -> new Gemeente(item.getKbonummer(), item.getNaam()))
                .sorted(Comparator.comparing(o -> o.naam))
                .toList();
    }

    record Gemeente(String kbonummer, String naam){}
}
