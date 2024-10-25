package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;


import java.time.LocalDateTime;

public record DocumentJSON(
    DocumentType type,
    String link,
    String originalFileName,
    LocalDateTime localDateTime
) {}
