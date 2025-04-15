package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Accommodation;

import java.util.List;

public interface GuestReservationService {
    public void addToTemporaryReservations(Long guestId, Long accommodationId);
    public List<Accommodation> viewTemporaryReservations(Long guestId);
    public void confirmAllReservations(Long guestId);
}
