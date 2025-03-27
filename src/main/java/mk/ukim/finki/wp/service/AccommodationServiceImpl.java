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

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public List<AccommodationResponseDto> listAll() {
        return accommodationRepository.findAll().stream().map(AccommodationResponseDto::new).toList();
    }

    @Override
    public AccommodationResponseDto addAccommodation(AccommodationDto accommodationDto) {
        Host host = hostRepository.findById(accommodationDto.getHostId()).get(); //exception if not present
        Guest guest = guestRepository.findById(Long.valueOf(accommodationDto.getGuestId())).get();
        List<Guest> guests = host.getGuests();
        guests.add(guest);

        host.setGuests(guests);

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
        accommodationRepository.deleteById(id);
    }

    @Override
    public AccommodationResponseDto update(Long id, AccommodationDto accommodationDto) {
        Accommodation accommodation = accommodationRepository.findById(id).get();
        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setNumRooms(accommodationDto.getNumRooms());

        Host host = hostRepository.findById(accommodationDto.getHostId()).get();
        accommodation.setHost(host);

        return new AccommodationResponseDto(accommodationRepository.save(accommodation));
    }

    @Override
    public void rent(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id).get();
        accommodation.setAvailable(false);
        accommodationRepository.save(accommodation);
    }
}
