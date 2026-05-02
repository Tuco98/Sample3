package org.example.rideSharingApp;

import java.util.List;

public interface MatchingStrategy {
    List<Driver> getCadidates(Location src, List<Driver> drivers, LocationService ls);
}
