package mk.ukim.finki.wp.dto;

import lombok.Data;
import mk.ukim.finki.wp.model.Category;
import mk.ukim.finki.wp.model.Host;
@Data
public class AccommodationDto {
    private String name;
    private Category category;
    private Long hostId;
    private Integer numRooms;
    private Integer guestId;


}
