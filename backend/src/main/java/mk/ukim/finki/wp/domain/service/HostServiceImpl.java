package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.application.dto.HostDto;
import mk.ukim.finki.wp.domain.model.Country;
import mk.ukim.finki.wp.domain.model.Host;
import mk.ukim.finki.wp.repository.CountryRepository;
import mk.ukim.finki.wp.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public Host update(Long id, HostDto dto) {
        Host host = hostRepository.findById(id).orElseThrow();
        host.setName(dto.getName());
        host.setSurname(dto.getSurname());
        host.setCountry(countryRepository.findById(dto.getCountryId()).orElseThrow());
        return hostRepository.save(host);
    }

    @Override
    public Host addHost(HostDto hostDto) {
        Host host = new Host();
        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(countryRepository.findById(hostDto.getCountryId()).orElseThrow());
        host.setGuests(new ArrayList<>());
        return hostRepository.save(host);
    }

}
