package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats;

public record ReservatieFeedbackJSON(
        String reservationStatus,
        String reservationMessage,
        String restingPlace,
        String cemeteryInstructionsURI
) {}
