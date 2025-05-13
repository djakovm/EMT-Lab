package mk.ukim.finki.wp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String surname;

    @ManyToOne(optional = false)
    Country country;

    @ManyToMany
    private List<Accommodation> temporaryReservations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "guest_host",
            joinColumns = @JoinColumn(name = "guest_id"),
            inverseJoinColumns = @JoinColumn(name = "host_id")
    )
    List<Host> hosts = new ArrayList<>();
}
