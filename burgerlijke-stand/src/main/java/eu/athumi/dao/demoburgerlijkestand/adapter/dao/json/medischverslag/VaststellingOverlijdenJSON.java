package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.ArtsJSON;

import java.time.LocalDateTime;
import java.util.List;

public record VaststellingOverlijdenJSON(
    String type,
    List<BezwarenTypeDTO> bezwaar,
    List<RisicoTypeDTO> risico,
    LocalDateTime datum,
    ArtsJSON arts
)
    implements MedischVerslag {
}
