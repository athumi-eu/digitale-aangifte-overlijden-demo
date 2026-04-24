package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.begraafplaats;

import java.time.OffsetDateTime;

public record FormDataJSON(
        OffsetDateTime dateReservation,
        String remainsDelivery,
        String remainsDestination,
        String remainsDestinationOtherText,
        String concession,
        String existingConcessionDetails,
        String remarks,
        CoffinDimensionsJSON coffinDimensions,
        UrnDimensionsJSON urnDimensions,
        Boolean familyWillAttend
) {}
