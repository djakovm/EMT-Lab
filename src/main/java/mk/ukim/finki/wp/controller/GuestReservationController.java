package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.ukim.finki.wp.model.Accommodation;
import mk.ukim.finki.wp.service.GuestReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests/{guestId}/reservations")
public class GuestReservationController {

    private final GuestReservationService guestReservationService;

    public GuestReservationController(GuestReservationService guestReservationService) {
        this.guestReservationService = guestReservationService;
    }

    @Operation(
            summary = "Add an accommodation to the guest's temporary reservation list",
            description = "Adds the accommodation to the temporary reservation list if it is available"
    )
    @ApiResponse(responseCode = "200", description = "Accommodation successfully added to temporary reservations")
    @PostMapping("/add/{accommodationId}")
    public ResponseEntity<String> addToReservations(
            @Parameter(description = "Guest ID") @PathVariable Long guestId,
            @Parameter(description = "Accommodation ID") @PathVariable Long accommodationId) {
        guestReservationService.addToTemporaryReservations(guestId, accommodationId);
        return ResponseEntity.ok("Added to temporary reservations");
    }

    @Operation(
            summary = "View the guest's temporary reservation list",
            description = "Returns a list of accommodations currently in the guest's temporary reservation list"
    )
    @ApiResponse(responseCode = "200", description = "Temporary reservations retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Accommodation>> view(
            @Parameter(description = "Guest ID") @PathVariable Long guestId) {
        return ResponseEntity.ok(guestReservationService.viewTemporaryReservations(guestId));
    }

    @Operation(
            summary = "Confirm all reservations for the guest",
            description = "Marks all accommodations in the guest's temporary reservation list as rented"
    )
    @ApiResponse(responseCode = "200", description = "All reservations confirmed and marked as rented")
    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(
            @Parameter(description = "Guest ID") @PathVariable Long guestId) {
        guestReservationService.confirmAllReservations(guestId);
        return ResponseEntity.ok("Confirmed and marked as rented");
    }
}
