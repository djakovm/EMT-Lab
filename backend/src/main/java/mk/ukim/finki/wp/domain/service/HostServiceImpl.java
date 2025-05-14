package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.domain.model.Host;
import mk.ukim.finki.wp.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }
}
