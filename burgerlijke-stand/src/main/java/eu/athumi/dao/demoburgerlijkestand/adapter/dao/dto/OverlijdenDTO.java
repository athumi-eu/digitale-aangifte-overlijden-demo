package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;

import java.time.LocalDateTime;

public record OverlijdenDTO(
    LocalDateTime tijdstip,
    String beschrijvingTijdstip,
    PlaatsDTO plaats
) {}
