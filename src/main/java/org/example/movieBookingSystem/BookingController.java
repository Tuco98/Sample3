package org.example.movieBookingSystem;

import java.util.List;

public class BookingController {
    BookingService bookingService = new BookingService();
    PaymentService paymentService = new PaymentService();
    ShowService showService = new ShowService();

    public void book(String showId, List<String> seatIds,String idempotencyKey){
        Show show = showService.getShow(showId);

        Booking booking = bookingService.createBooking(showId, seatIds);
        Payment payment = paymentService.processPayment(booking.bookingId, idempotencyKey);
        if(payment.paymentStatus == PaymentStatus.SUCCESS){
            bookingService.confirmBooking(booking.bookingId);
        }else{
            bookingService.cancelBooking(booking.bookingId);
        }
    }
}
