package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.dto.AccommodationDto;
import mk.ukim.finki.wp.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.model.Accommodation;
import mk.ukim.finki.wp.model.Guest;
import mk.ukim.finki.wp.model.Host;
import mk.ukim.finki.wp.repository.AccommodationRepository;
import mk.ukim.finki.wp.repository.GuestRepository;
import mk.ukim.finki.wp.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    HostRepository hostRepository,
                                    GuestRepository guestRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
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

        Guest guest = guestRepository.findById(Long.valueOf(accommodationDto.getGuestId()))
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + accommodationDto.getGuestId()));

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
}
