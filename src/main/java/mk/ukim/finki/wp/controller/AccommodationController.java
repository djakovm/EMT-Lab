package mk.ukim.finki.wp.controller;

import mk.ukim.finki.wp.dto.AccommodationDto;
import mk.ukim.finki.wp.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.model.Guest;
import mk.ukim.finki.wp.repository.GuestRepository;
import mk.ukim.finki.wp.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final GuestRepository guestRepository;

    public AccommodationController(AccommodationService accommodationService, GuestRepository guestRepository) {
        this.accommodationService = accommodationService;
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<AccommodationResponseDto> listAll(){
        return accommodationService.listAll();
    }

    @GetMapping("/guests")
    public List<Guest> getGuests(){
        return guestRepository.findAll();
    }

    @PostMapping
    public AccommodationResponseDto create(@RequestBody AccommodationDto accommodationDto){
        return accommodationService.addAccommodation(accommodationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        accommodationService.deleteAccommodation(id);
    }

    @PutMapping("/{id}")
    public AccommodationResponseDto update(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto){
        return accommodationService.update(id, accommodationDto);
    }

    @PutMapping("/{id}/rent")
    public void rent(@PathVariable Long id){
        accommodationService.rent(id);
    }
}

