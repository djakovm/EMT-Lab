package mk.ukim.finki.wp.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.model.*;
import mk.ukim.finki.wp.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccommodationRepository accommodationRepository,
                           HostRepository hostRepository,
                           CountryRepository countryRepository,
                           GuestRepository guestRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Country macedonia = new Country();
        macedonia.setName("Macedonia");
        macedonia.setContinent("Europe");
        macedonia = countryRepository.save(macedonia);

        Country germany = new Country();
        germany.setName("Germany");
        germany.setContinent("Europe");
        germany = countryRepository.save(germany);

        Guest guest = new Guest();
        guest.setName("Guest");
        guest.setSurname("GG");
        guest.setCountry(macedonia);
        guest = guestRepository.save(guest);

        List<Guest> guestList = List.of(guest);

        Host ana = new Host();
        ana.setName("Ana");
        ana.setSurname("Stanoeva");
        ana.setCountry(macedonia);
        ana.setGuests(guestList);
        ana = hostRepository.save(ana);

        Host marko = new Host();
        marko.setName("Marko");
        marko.setSurname("Petrov");
        marko.setCountry(macedonia);
        marko.setGuests(guestList);
        marko = hostRepository.save(marko);

        Host lena = new Host();
        lena.setName("Lena");
        lena.setSurname("Müller");
        lena.setCountry(germany);
        lena.setGuests(guestList);
        lena = hostRepository.save(lena);

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

        // Step 4: Add test users
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user"));
        user1.setName("Test");
        user1.setSurname("User");
        user1.setRole(Role.USER);
        userRepository.save(user1);

        User host1 = new User();
        host1.setUsername("host");
        host1.setPassword(passwordEncoder.encode("host"));
        host1.setName("Hosty");
        host1.setSurname("Hostovski");
        host1.setRole(Role.HOST);
        userRepository.save(host1);
    }
}
