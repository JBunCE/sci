package org.noctisdev.sciapi.web.dto.request;

public record PublicationRequest(
    String title,
    String author,
    String content
) { }
