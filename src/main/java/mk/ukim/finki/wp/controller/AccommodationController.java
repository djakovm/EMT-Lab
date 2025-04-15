package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import mk.ukim.finki.wp.dto.AccommodationDto;
import mk.ukim.finki.wp.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.model.Guest;
import mk.ukim.finki.wp.repository.GuestRepository;
import mk.ukim.finki.wp.service.AccommodationService;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Returns a list of all accommodations")
    @ApiResponse(responseCode = "200", description = "List successfully returned")
    @GetMapping
    public ResponseEntity<List<AccommodationResponseDto>> listAll() {
        return ResponseEntity.ok(accommodationService.listAll());
    }

    @Operation(summary = "Returns all guests")
    @ApiResponse(responseCode = "200", description = "Guests successfully returned")
    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getGuests() {
        return ResponseEntity.ok(guestRepository.findAll());
    }

    @Operation(summary = "Creates a new accommodation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping
    public ResponseEntity<AccommodationResponseDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO for creating accommodation")
            @org.springframework.web.bind.annotation.RequestBody AccommodationDto accommodationDto) {
        return ResponseEntity.ok(accommodationService.addAccommodation(accommodationDto));
    }

    @Operation(summary = "Deletes an accommodation by ID")
    @ApiResponse(responseCode = "204", description = "Accommodation successfully deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the accommodation to delete") @PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Updates an accommodation by ID")
    @ApiResponse(responseCode = "200", description = "Accommodation successfully updated")
    @PutMapping("/{id}")
    public ResponseEntity<AccommodationResponseDto> update(
            @Parameter(description = "ID of the accommodation to update") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO with updated data")
            @org.springframework.web.bind.annotation.RequestBody AccommodationDto accommodationDto) {
        return ResponseEntity.ok(accommodationService.update(id, accommodationDto));
    }

    @Operation(summary = "Marks an accommodation as rented")
    @ApiResponse(responseCode = "200", description = "Accommodation successfully marked as rented")
    @PutMapping("/{id}/rent")
    public ResponseEntity<Void> rent(
            @Parameter(description = "ID of the accommodation to rent") @PathVariable Long id) {
        accommodationService.rent(id);
        return ResponseEntity.ok().build();
    }
}
