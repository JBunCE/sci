package org.noctisdev.sciapi.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.noctisdev.sciapi.persistance.entities.Publication;
import org.noctisdev.sciapi.persistance.repositories.IPublicationRepository;
import org.noctisdev.sciapi.web.dto.request.PublicationRequest;
import org.noctisdev.sciapi.web.dto.response.BaseResponse;
import org.noctisdev.sciapi.web.dto.response.PublicationResponse;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicationServiceTest {

    @InjectMocks
    private PublicationService publicationService;

    @Mock
    private IPublicationRepository repository;

    private PublicationRequest publicationRequestDummy;
    private Publication publicationDummy;
    private UUID uuid = UUID.randomUUID();

    @BeforeEach
    public void init() {
        publicationRequestDummy = createPublicationRequestDummy();
        publicationDummy = createPublicationDummy();
    }

    @Test
    void givenCreatePublicationRequestOk_WhenCreatePublication_ThenAllOk() {
        when(repository.save(any(Publication.class))).thenAnswer(i -> {
            Publication publication = i.getArgument(0);
            publication.setUuid(UUID.randomUUID());
            return publication;
        });

        BaseResponse response = publicationService.createPublication(publicationRequestDummy);
        PublicationResponse savedPublication = (PublicationResponse) response.getData();

        assertNotNull(savedPublication);
        assertEquals(publicationRequestDummy.title(), savedPublication.title());
        assertEquals(publicationRequestDummy.author(), savedPublication.author());
        assertEquals(publicationRequestDummy.content(), savedPublication.content());
    }

    @Test
    void givenUpdatePublicationRequestOk_WhenUpdatePublication_ThenAllOk() {

        when(repository.findById(uuid)).thenReturn(Optional.of(publicationDummy));
        when(repository.save(any(Publication.class))).thenAnswer(i -> i.getArgument(0));

        BaseResponse response = publicationService.updatePublication(publicationRequestDummy, uuid);
        PublicationResponse updatedPublication = (PublicationResponse) response.getData();

        assertNotNull(updatedPublication);
        assertEquals(publicationRequestDummy.title(), updatedPublication.title());
        assertEquals(publicationRequestDummy.author(), updatedPublication.author());
        assertEquals(publicationRequestDummy.content(), updatedPublication.content());
    }

    private PublicationRequest createPublicationRequestDummy() {
        return new PublicationRequest("Titulo 1", "author 1", "content 1");
    }

    private Publication createPublicationDummy() {
        Publication publication = new Publication();

        publication.setUuid(uuid);
        publication.setTitle("t");
        publication.setAuthor("a");
        publication.setContent("c");
        publication.setCreatedAt(LocalDate.now());
        publication.setUpdatedAt(LocalDate.now());
        publication.setDeletedAt(null);

        return publication;
    }
}