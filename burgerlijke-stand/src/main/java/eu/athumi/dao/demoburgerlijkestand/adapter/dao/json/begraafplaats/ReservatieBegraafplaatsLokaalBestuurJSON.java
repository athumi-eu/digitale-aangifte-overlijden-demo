package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ReservatieBegraafplaatsLokaalBestuurJSON(
        UUID id,
        String caseNumber,
        String reservationStatus,
        String cemeteryName,
        UitvaartOndernemerJSON funeralHome,
        String choiceOfFuneral,
        String ashDestination,
        String reservationMessage,
        OffsetDateTime submittedDate,
        OffsetDateTime changedDate,
        OffsetDateTime feedbackDate,
        OffsetDateTime detailsFinalRestingPlaceUploadDate,
        String detailsFinalRestingPlaceUrl,
        String restingPlace,
        String cemeteryInstructionsUrl,
        FormDataJSON formData
) {}
