package mk.ukim.finki.wp.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.model.*;
import mk.ukim.finki.wp.repository.AccommodationRepository;
import mk.ukim.finki.wp.repository.HostRepository;
import mk.ukim.finki.wp.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AccommodationRepository accommodationRepository,
                           HostRepository hostRepository,
                           CountryRepository countryRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        // Step 1: Save countries
        Country macedonia = new Country();
        macedonia.setName("Macedonia");
        macedonia.setContinent("Europe");
        macedonia = countryRepository.save(macedonia); // save and reassign with id

        Country germany = new Country();
        germany.setName("Germany");
        germany.setContinent("Europe");
        germany = countryRepository.save(germany);

        // Step 2: Create and save hosts
        Host ana = new Host();
        ana.setName("Ana");
        ana.setSurname("Stanoeva");
        ana.setCountry(macedonia);
        ana = hostRepository.save(ana);

        Host marko = new Host();
        marko.setName("Marko");
        marko.setSurname("Petrov");
        marko.setCountry(macedonia);
        marko = hostRepository.save(marko);

        Host lena = new Host();
        lena.setName("Lena");
        lena.setSurname("Müller");
        lena.setCountry(germany);
        lena = hostRepository.save(lena);

        // Step 3: Create and save accommodations
        Accommodation a1 = new Accommodation();
        a1.setName("Sunny Apartment");
        a1.setCategory(Category.APARTMENT);
        a1.setHost(ana);
        a1.setNumRooms(3);
        a1.setAvailable(true);
        accommodationRepository.save(a1);

        Accommodation a2 = new Accommodation();
        a2.setName("Budget Room");
        a2.setCategory(Category.ROOM);
        a2.setHost(marko);
        a2.setNumRooms(1);
        a2.setAvailable(true);
        accommodationRepository.save(a2);

        Accommodation a3 = new Accommodation();
        a3.setName("Luxury House");
        a3.setCategory(Category.HOUSE);
        a3.setHost(lena);
        a3.setNumRooms(5);
        a3.setAvailable(true);
        accommodationRepository.save(a3);
    }
}
