package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag.AardOverlijdenType;

import java.util.List;

public record OverlijdensToestandJSON(
    AardOverlijdenType aard,
    List<MedischVerslag> medischeVerslagen
)
    implements Type {
    @Override
    public String type() {
        return "Overlijdenstoestand";
    }
}
