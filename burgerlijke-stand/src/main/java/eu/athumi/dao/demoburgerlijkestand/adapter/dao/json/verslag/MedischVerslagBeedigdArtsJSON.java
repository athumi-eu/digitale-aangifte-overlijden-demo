package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;

import java.time.LocalDateTime;

public record MedischVerslagBeedigdArtsJSON(String type, LocalDateTime datum, BeedigdArtsJSON arts)
    implements MedischVerslag {}
