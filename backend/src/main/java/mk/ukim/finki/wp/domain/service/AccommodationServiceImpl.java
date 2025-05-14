package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.application.dto.AccommodationDto;
import mk.ukim.finki.wp.application.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.domain.model.Accommodation;
import mk.ukim.finki.wp.domain.model.Guest;
import mk.ukim.finki.wp.domain.model.Host;
import mk.ukim.finki.wp.domain.model.Reservation;
import mk.ukim.finki.wp.repository.AccommodationRepository;
import mk.ukim.finki.wp.repository.GuestRepository;
import mk.ukim.finki.wp.repository.HostRepository;
import mk.ukim.finki.wp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;


    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    HostRepository hostRepository,
                                    GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<AccommodationResponseDto> listAll() {
        return accommodationRepository.findAll().stream()
                .map(AccommodationResponseDto::new)
                .toList();
    }

    @Override
    public AccommodationResponseDto addAccommodation(AccommodationDto accommodationDto) {
        Host host = hostRepository.findById(accommodationDto.getHostId())
                .orElseThrow(() -> new RuntimeException("Host not found with ID: " + accommodationDto.getHostId()));

//        Guest guest = guestRepository.findById(Long.valueOf(accommodationDto.getGuestId()))
//                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + accommodationDto.getGuestId())); //TODO

        Guest guest = guestRepository.findById(1L).get();

        host.getGuests().add(guest);

        Accommodation accommodation = new Accommodation();
        accommodation.setName(accommodationDto.getName());
        accommodation.setHost(host);
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setAvailable(true);

        return new AccommodationResponseDto(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteAccommodation(Long id) {
        if (!accommodationRepository.existsById(id)) {
            throw new RuntimeException("Accommodation not found with ID: " + id);
        }
        accommodationRepository.deleteById(id);
    }

    @Override
    public AccommodationResponseDto update(Long id, AccommodationDto accommodationDto) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accommodation not found with ID: " + id));

        Host host = hostRepository.findById(accommodationDto.getHostId())
                .orElseThrow(() -> new RuntimeException("Host not found with ID: " + accommodationDto.getHostId()));

        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setHost(host);

        return new AccommodationResponseDto(accommodationRepository.save(accommodation));
    }

    @Override
    public void rent(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accommodation not found with ID: " + id));

        accommodation.setAvailable(false);
        accommodationRepository.save(accommodation);
    }

    @Override
    public void reserve(Long accommodationId, Long guestId, LocalDate from, LocalDate to) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        List<Reservation> existingReservations = accommodation.getReservations();

        boolean overlaps = existingReservations.stream().anyMatch(res ->
                !(res.getDateTo().isBefore(from) || res.getDateFrom().isAfter(to))
        );

        if (overlaps) {
            throw new RuntimeException("This accommodation is already reserved in that date range.");
        }

        Reservation reservation = new Reservation();
        reservation.setAccommodation(accommodation);
        reservation.setGuest(guest);
        reservation.setDateFrom(from);
        reservation.setDateTo(to);

        reservationRepository.save(reservation);

        accommodation.getReservations().add(reservation);
        accommodationRepository.save(accommodation);
    }

}
