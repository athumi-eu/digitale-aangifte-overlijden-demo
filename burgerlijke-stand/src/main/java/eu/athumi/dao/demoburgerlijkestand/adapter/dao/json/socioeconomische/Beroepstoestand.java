package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische;


import java.util.List;

public record Beroepstoestand(
    BeroepstoestandType type,
    String beroepstoestandAndere,
    boolean beroepsInformatieGekend,
    List<Beroep> beroepen
) {
}
