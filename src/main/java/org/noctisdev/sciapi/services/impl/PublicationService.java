package org.noctisdev.sciapi.services.impl;

import org.noctisdev.sciapi.persistance.entities.Publication;
import org.noctisdev.sciapi.persistance.repositories.IPublicationRepository;
import org.noctisdev.sciapi.services.IPublicationService;
import org.noctisdev.sciapi.web.dto.request.PublicationRequest;
import org.noctisdev.sciapi.web.dto.response.BaseResponse;
import org.noctisdev.sciapi.web.dto.response.PublicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    private IPublicationRepository repository;

    @Override
    public BaseResponse getPublications() {
        List<PublicationResponse> publications = repository.findAll()
                .stream().map(this::toPublicationResponse).toList();

        return BaseResponse.builder()
                .data(publications)
                .message("The publications were retrieved successfully")
                .success(Boolean.TRUE)
                .status(200).httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse createPublication(PublicationRequest request) {
        Publication publication = toPublication(request);
        Publication savedPublication = repository.save(publication);

        return BaseResponse.builder()
                .data(toPublicationResponse(savedPublication))
                .message("The publication was saved successfully")
                .success(Boolean.TRUE)
                .status(200).httpStatus(HttpStatus.OK).build();
    }

    private Publication toPublication(PublicationRequest request) {
        Publication publication = new Publication();

        publication.setTitle(request.title());
        publication.setAuthor(request.author());
        publication.setContent(request.content());
        publication.setCreatedAt(LocalDate.now());
        publication.setUpdatedAt(LocalDate.now());

        return publication;
    }

    private PublicationResponse toPublicationResponse(Publication publication) {
        return new PublicationResponse(
            publication.getUuid(),
            publication.getTitle(),
            publication.getAuthor(),
            publication.getContent(),
            publication.getCreatedAt(),
            publication.getUpdatedAt(),
            publication.getDeletedAt()
        );
    }

}
