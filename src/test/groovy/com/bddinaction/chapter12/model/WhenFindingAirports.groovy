package com.bddinaction.chapter12.model

import com.bddinaction.chapter12.services.AirportService
import spock.lang.Ignore

class WhenFindingAirports {

    AirportService airportService;

    @Ignore("Pending")
    def "should find airport by airport code"() {
        given:
            // TODO: create an airport service
        when:
            def airport = airportService.findAirportByCode("SYD")
        then:
            airport.name == "Sydney"
    }

}
