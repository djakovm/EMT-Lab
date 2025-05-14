package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.wp.application.dto.AccommodationDto;
import mk.ukim.finki.wp.application.dto.AccommodationResponseDto;
import mk.ukim.finki.wp.domain.model.Country;
import mk.ukim.finki.wp.domain.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @Operation(summary = "Returns a list of all countries")
    @ApiResponse(responseCode = "200", description = "List successfully returned")
    @GetMapping
    public ResponseEntity<List<Country>> listAll() {
        return ResponseEntity.ok(countryService.listAll());
    }

    @PostMapping
    public ResponseEntity<Country> create(
            @RequestBody Country country) {
        return ResponseEntity.ok(countryService.addCountry(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(
            @PathVariable Long id,
            @RequestBody Country country) {
        return ResponseEntity.ok(countryService.update(id, country));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }


}
