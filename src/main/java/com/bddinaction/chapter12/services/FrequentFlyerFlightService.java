package com.bddinaction.chapter12.services;

import com.bddinaction.chapter12.model.Airport;
import com.bddinaction.chapter12.model.Flight;

public class FrequentFlyerFlightService implements FlightService {

    private final AirportService airportService;

    public FrequentFlyerFlightService(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public Flight findFlightByNumber(String airportCode, String flightNumber) {
        Airport departureAirport = airportService.findAirportByCode(airportCode);
        return new Flight(flightNumber,departureAirport, new Airport("SYD","Sydney"), "06:00");
    }
}
