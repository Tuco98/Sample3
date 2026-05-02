package org.example.movieBookingSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.example.movieBookingSystem.BookingService.bookingMap;

public class PaymentService {
    static Map<String, Payment> paymentDb = new HashMap<>();

    public Payment processPayment(String bookingId, String idempotencyKey){


        if (paymentDb.containsKey(idempotencyKey)){
            return paymentDb.get(idempotencyKey);
        }

        Booking booking = bookingMap.get(bookingId);
        Payment payment = new Payment();
        payment.idempotencyKey = idempotencyKey;
        payment.paymentId = UUID.randomUUID().toString();
        payment.bookingId = bookingId;
        payment.paymentStatus = PaymentStatus.IN_PROGRESS;
        paymentDb.put(idempotencyKey,payment);

//        double totalAmount = booking.seats.stream().mapToDouble(e->e.lockedPrice).sum();

        boolean res = doPayment(booking.totalAmount);

        if(res){
            payment.paymentStatus = PaymentStatus.SUCCESS;
            booking.status = BookingStatus.COMPLETE;
        }else{
            payment.paymentStatus = PaymentStatus.FAILURE;
        }
        return payment;

    }

    private boolean doPayment(double totalAmount) {
        return true;
    }


}
