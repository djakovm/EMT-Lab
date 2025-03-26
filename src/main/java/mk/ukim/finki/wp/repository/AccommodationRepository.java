package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.dto.AccommodationDto;
import mk.ukim.finki.wp.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
