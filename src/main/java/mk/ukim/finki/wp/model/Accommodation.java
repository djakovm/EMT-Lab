package mk.ukim.finki.wp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    Host host;
    int numRooms;
    boolean isAvailable;
}
