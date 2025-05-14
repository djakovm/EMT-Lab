package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import mk.ukim.finki.wp.application.dto.AccommodationDto;
import mk.ukim.finki.wp.application.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.domain.model.Guest;
import mk.ukim.finki.wp.repository.GuestRepository;
import mk.ukim.finki.wp.domain.service.AccommodationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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
    @PostMapping("/{id}/rent")
    public ResponseEntity<String> reserve(
            @PathVariable Long id,
            @RequestParam Long guestId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        accommodationService.reserve(id, guestId, from, to);
        return ResponseEntity.ok("Reservation created successfully.");
    }
}
