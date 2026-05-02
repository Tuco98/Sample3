package org.example.movieBookingSystem;

import java.util.Date;
import java.util.Map;

public class Show {
    String showId;
    String movieId;
    String theatreId; //may not be required
    String screenId;
    Map<String, ShowSeat> seatMap;
    Date date;
}
