package mk.ukim.finki.wp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Accommodation accommodation;
}
