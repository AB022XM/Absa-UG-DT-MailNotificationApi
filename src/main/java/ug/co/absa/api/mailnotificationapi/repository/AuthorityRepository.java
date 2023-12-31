package ug.co.absa.api.mailnotificationapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ug.co.absa.api.mailnotificationapi.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
