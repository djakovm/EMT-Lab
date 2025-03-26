package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.dto.AccommodationDto;
import mk.ukim.finki.wp.dto.AccommodationResponseDto;

import java.util.List;

public interface AccommodationService {
    List<AccommodationResponseDto> listAll();

    AccommodationResponseDto addAccommodation(AccommodationDto accommodationDto);

    void deleteAccommodation(Long id);

    AccommodationResponseDto update(Long id, AccommodationDto accommodationDto);

    void rent(Long id);
}
