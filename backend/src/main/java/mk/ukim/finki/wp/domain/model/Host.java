package mk.ukim.finki.wp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String surname;

    @ManyToOne(optional = false)
    Country country;

    @ManyToMany(mappedBy = "hosts")
    List<Guest> guests;
}
