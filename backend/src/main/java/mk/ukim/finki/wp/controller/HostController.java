package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.ukim.finki.wp.application.dto.HostDto;
import mk.ukim.finki.wp.domain.model.Country;
import mk.ukim.finki.wp.domain.model.Host;
import mk.ukim.finki.wp.domain.service.CountryService;
import mk.ukim.finki.wp.domain.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Host> create(
            @RequestBody  HostDto hostDto) {
        return ResponseEntity.ok(hostService.addHost(hostDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Host> update(
            @PathVariable Long id,
            @RequestBody HostDto host) {
        return ResponseEntity.ok(hostService.update(id, host));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        hostService.deleteHost(id);
        return ResponseEntity.noContent().build();
    }
}
