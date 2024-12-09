package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische;

import java.time.LocalDateTime;

public record SEGUitvaart(
        SEGVO overledene,
        SEGVO moeder,
        SEGVO vaderOfMeemoeder,
        LocalDateTime aangepastOp
) {}