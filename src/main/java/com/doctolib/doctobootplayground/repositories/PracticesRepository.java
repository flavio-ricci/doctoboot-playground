package com.doctolib.doctobootplayground.repositories;

import com.doctolib.doctobootplayground.entities.PracticeEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticesRepository extends JpaRepository<PracticeEntity, UUID> {
    List<PracticeEntity> findByOrganizationId(Long organizationId);
}
