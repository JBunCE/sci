package org.noctisdev.sciapi.persistance.repositories;

import org.noctisdev.sciapi.persistance.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPublicationRepository extends JpaRepository<Publication, UUID> {
}
