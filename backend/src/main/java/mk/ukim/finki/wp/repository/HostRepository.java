package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.domain.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
