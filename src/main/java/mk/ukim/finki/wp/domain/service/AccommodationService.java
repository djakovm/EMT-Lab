package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.application.dto.AccommodationDto;
import mk.ukim.finki.wp.application.dto.AccommodationResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationService {
    List<AccommodationResponseDto> listAll();

    AccommodationResponseDto addAccommodation(AccommodationDto accommodationDto);

    void deleteAccommodation(Long id);

    AccommodationResponseDto update(Long id, AccommodationDto accommodationDto);

    void rent(Long id);

    void reserve(Long accommodationId, Long guestId, LocalDate from, LocalDate to);

}
