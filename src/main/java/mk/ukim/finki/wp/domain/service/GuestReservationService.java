package mk.ukim.finki.wp.domain.service;

import mk.ukim.finki.wp.domain.model.Accommodation;

import java.util.List;

public interface GuestReservationService {
    public void addToTemporaryReservations(Long guestId, Long accommodationId);
    public List<Accommodation> viewTemporaryReservations(Long guestId);
    public void confirmAllReservations(Long guestId);
}
