package org.noctisdev.sciapi.web;

import org.noctisdev.sciapi.services.IPublicationService;
import org.noctisdev.sciapi.web.dto.request.PublicationRequest;
import org.noctisdev.sciapi.web.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    private IPublicationService service;

    @GetMapping
    public ResponseEntity<BaseResponse> getPublications() {
        return service.getPublications().apply();
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPublication(@RequestBody PublicationRequest request) {
        return service.createPublication(request).apply();
    }

}
