package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.ukim.finki.wp.domain.model.Country;
import mk.ukim.finki.wp.domain.model.Host;
import mk.ukim.finki.wp.domain.service.CountryService;
import mk.ukim.finki.wp.domain.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }


    @Operation(summary = "Returns a list of all hosts")
    @ApiResponse(responseCode = "200", description = "List successfully returned")
    @GetMapping
    public ResponseEntity<List<Host>> listAll() {
        return ResponseEntity.ok(hostService.listAll());
    }

}
