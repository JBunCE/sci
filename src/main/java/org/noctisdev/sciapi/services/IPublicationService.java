package org.noctisdev.sciapi.services;

import org.noctisdev.sciapi.persistance.entities.Publication;
import org.noctisdev.sciapi.web.dto.request.PublicationRequest;
import org.noctisdev.sciapi.web.dto.response.BaseResponse;

import java.util.List;

public interface IPublicationService {

    BaseResponse getPublications();
    BaseResponse createPublication(PublicationRequest request);

}
