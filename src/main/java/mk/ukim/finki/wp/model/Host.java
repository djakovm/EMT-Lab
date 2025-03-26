package mk.ukim.finki.wp.model;

import jakarta.persistence.*;
import lombok.Data;

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
}
