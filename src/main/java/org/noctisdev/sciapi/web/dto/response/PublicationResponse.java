package org.noctisdev.sciapi.web.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PublicationResponse(
    UUID id,
    String title,
    String author,
    String content,
    LocalDate createdAt,
    LocalDate updatedAt,
    LocalDate deletedAt
) {
}
