package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verslag;


import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.medischverslag.MedischVerslag;

import java.time.LocalDateTime;

public record MedischVerslagBeedigdArtsJSON(String type, LocalDateTime datum, BeedigdArtsJSON arts)
    implements MedischVerslag {}
