package mk.ukim.finki.wp.controller;

import mk.ukim.finki.wp.dto.AccommodationDto;
import mk.ukim.finki.wp.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<AccommodationResponseDto> listAll(){
        return accommodationService.listAll();
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

