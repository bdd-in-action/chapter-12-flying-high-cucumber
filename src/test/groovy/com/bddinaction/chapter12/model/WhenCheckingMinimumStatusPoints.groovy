package com.bddinaction.chapter12.model

import spock.lang.Specification

import static com.bddinaction.chapter12.model.FrequentFlyerStatus.*

class WhenCheckingMinimumStatusPoints extends Specification {

    def "should know what the minimum points are for a given status level"() {
        expect:
             FrequentFlyerStatus.statusLevelFor(points) == expectedStatus
        where:
            points  | expectedStatus
            0       | BRONZE
            299     | BRONZE
            300     | SILVER
            699     | SILVER
            700     | GOLD
            1499    | GOLD
            1500    | PLATINUM
    }
}

