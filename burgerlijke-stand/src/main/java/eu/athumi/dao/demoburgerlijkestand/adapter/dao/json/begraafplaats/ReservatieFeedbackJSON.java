package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats;

import java.util.UUID;

public record ReservatieFeedbackJSON(
        UUID id,
        String reservationStatus,
        String reservationMessage,
        String restingPlace,
        String cemeteryInstructionsURI
) {}
