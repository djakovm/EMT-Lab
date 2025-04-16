package mk.ukim.finki.wp.application.dto;

import lombok.Data;
import mk.ukim.finki.wp.domain.model.Category;

@Data
public class AccommodationDto {
    private String name;
    private Category category;
    private Long hostId;
    private Integer numRooms;
    private Integer guestId;


}
