package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.UitvaartOndernemerJSON;

import java.util.Objects;

public record UitvaartOndernemerParser(UitvaartOndernemerJSON json) {

    public String naam() {
        if (Objects.isNull(json) || Objects.isNull(json.naam())) {
            return "/";
        }
        return json.naam();
    }

    public String kboNummer() {
        if (Objects.isNull(json) || Objects.isNull(json.kboNummer())) {
            return "/";
        }
        return json.kboNummer();
    }

    public String opgestartOp() {
        if (Objects.isNull(json) || Objects.isNull(json.opgestartOp())) {
            return "/";
        }
        return TijdstipParser.parseLocalDateTime(json.opgestartOp());
    }


}
