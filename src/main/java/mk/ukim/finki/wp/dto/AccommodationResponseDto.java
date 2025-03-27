package mk.ukim.finki.wp.dto;

import lombok.Data;
import mk.ukim.finki.wp.model.Accommodation;
import mk.ukim.finki.wp.model.Category;
import mk.ukim.finki.wp.model.Guest;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccommodationResponseDto {
    private Long id;
    private String name;
    private Category category;
    private String hostFullName;
    private String countryName;
    private Integer numRooms;
    private boolean isAvailable;

    public AccommodationResponseDto(Accommodation accommodation) {
        this.id = accommodation.getId();
        this.name = accommodation.getName();
        this.category = accommodation.getCategory();
        this.hostFullName = accommodation.getHost().getName() + " " + accommodation.getHost().getSurname();
        this.countryName = accommodation.getHost().getCountry().getName();
        this.numRooms = accommodation.getNumRooms();
        this.isAvailable = accommodation.isAvailable();
    }

}