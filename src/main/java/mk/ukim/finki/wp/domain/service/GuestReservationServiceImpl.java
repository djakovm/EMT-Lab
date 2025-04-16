package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.domain.model.Accommodation;
import mk.ukim.finki.wp.domain.model.Guest;
import mk.ukim.finki.wp.repository.AccommodationRepository;
import mk.ukim.finki.wp.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GuestReservationServiceImpl implements GuestReservationService {
    private final GuestRepository guestRepository;
    private final AccommodationRepository accommodationRepository;

    public GuestReservationServiceImpl(GuestRepository guestRepository,
                                   AccommodationRepository accommodationRepository) {
        this.guestRepository = guestRepository;
        this.accommodationRepository = accommodationRepository;
    }

    public void addToTemporaryReservations(Long guestId, Long accommodationId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        Accommodation acc = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        if (!acc.isAvailable()) {
            throw new RuntimeException("Accommodation is not available.");
        }

        if (!guest.getTemporaryReservations().contains(acc)) {
            guest.getTemporaryReservations().add(acc);
            guestRepository.save(guest);
        }
    }

    public List<Accommodation> viewTemporaryReservations(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        return guest.getTemporaryReservations();
    }

    public void confirmAllReservations(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        for (Accommodation acc : guest.getTemporaryReservations()) {
            acc.setAvailable(false);
            accommodationRepository.save(acc);
        }

        guest.getTemporaryReservations().clear();
        guestRepository.save(guest);
    }
}
