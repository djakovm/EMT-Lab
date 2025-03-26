package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
