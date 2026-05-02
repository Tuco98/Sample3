package org.example.movieBookingSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.example.movieBookingSystem.ShowService.showMap;

public class BookingService {
    public static Map<String, Booking> bookingMap = new HashMap<>();
    SeatLockingService seatLockingService = new SeatLockingService();
    PaymentService paymentService = new PaymentService();

    public Booking createBooking(String showId, List<String> seats){

        Show show = showMap.get(showId);

        boolean locked = seatLockingService.lockSeats(showId,seats);

        if(!locked){
            throw new RuntimeException("All seats not available");
        }

        Booking booking = new Booking();
        booking.bookingId = UUID.randomUUID().toString();
        booking.status = BookingStatus.IN_PROGRESS;
        booking.showId = showId;
        booking.seats = seats;
        double total = 0;
        for(String id:seats){
            total+=show.seatMap.get(id).lockedPrice;
        }
        booking.totalAmount = total;
        bookingMap.put(booking.bookingId,booking);

        return booking;

    }

    public void cancelBooking(String  bookingId){
        bookingMap.get(bookingId).status = BookingStatus.CANCELLED;
    }

    public void confirmBooking(String bookingId){
        bookingMap.get(bookingId).status = BookingStatus.COMPLETE;
    }
}
