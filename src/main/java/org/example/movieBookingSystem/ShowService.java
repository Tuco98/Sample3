package org.example.movieBookingSystem;

import java.util.HashMap;
import java.util.Map;

public class ShowService {
    public static Map<String, Show> showMap = new HashMap<>();

    public void addShow(Show show){
        showMap.put(show.showId, show);
    }

    public Show getShow(String showId){
        return showMap.get(showId);
    }
}
