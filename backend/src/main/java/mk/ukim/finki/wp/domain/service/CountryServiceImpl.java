package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.domain.model.Country;
import mk.ukim.finki.wp.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements  CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Country update(Long id, Country country) {
        Country oldCountry = countryRepository.findById(id).orElseThrow(RuntimeException::new);
        oldCountry.setName(country.getName());
        oldCountry.setContinent(country.getContinent());

        return countryRepository.save(oldCountry);
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }
}
