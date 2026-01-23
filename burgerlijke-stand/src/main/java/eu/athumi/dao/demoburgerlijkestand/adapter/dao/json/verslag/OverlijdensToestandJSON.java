package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Type;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;

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
