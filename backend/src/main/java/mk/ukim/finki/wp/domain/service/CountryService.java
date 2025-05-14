package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.application.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.domain.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> listAll();

    void deleteCountry(Long id);

    Country update(Long id, Country country);

    Country addCountry(Country country);
}
