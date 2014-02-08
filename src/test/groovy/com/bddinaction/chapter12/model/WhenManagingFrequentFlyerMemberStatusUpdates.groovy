package com.bddinaction.chapter12.model

import spock.lang.Specification

import static com.bddinaction.chapter12.model.FrequentFlyerStatus.*

class WhenManagingFrequentFlyerMemberStatusUpdates extends Specification {

    def "A frequent flyer requires a first name, a last name, and a frequent flyer number"() {
        when:
            def member = FrequentFlyer.withFrequentFlyerNumber("12345678").named("Joe","Bloggs")
        then:
            member.frequentFlyerNumber == "12345678"
        and:
            member.firstName == "Joe"
        and:
            member.lastName == "Bloggs"
    }

    def "a new frequent flyer should have Bronze status"() {
        given:
            def member = FrequentFlyer.withFrequentFlyerNumber("12345678").named("Joe","Bloggs")
        when:
            def status = member.status
        then:
            status == FrequentFlyerStatus.BRONZE
    }


    def "should be able to upgrade a frequent flyer status"() {
        given:
            def member = FrequentFlyer.withFrequentFlyerNumber("12345678").named("Joe","Bloggs")
        when:
            def updatedMember = member.withStatus(FrequentFlyerStatus.SILVER)
        then:
            updatedMember.status == FrequentFlyerStatus.SILVER
    }

    def "a member should be able to earn extra status points"() {
        given:
            def member = FrequentFlyer.withFrequentFlyerNumber("12345678").
                    named("Joe","Bloggs").
                    withStatusPoints(initialPoints)
        when:
            member.earns(additionalPoints).statusPoints()
        then:
            member.statusPoints == expectedPoints
        where:
            initialPoints | additionalPoints  | expectedPoints
            0             | 100               | 100
            100           | 50                | 150
    }

    def "should upgrade status when enough status points are acquired"() {
        given: "a frequent flyer member with some points"
            def member = FrequentFlyer.withFrequentFlyerNumber("12345678").
                    named("Joe","Bloggs").
                    withStatusPoints(initialPoints).
                    withStatus(initialStatus)
        when: "he earns some extra points on a flight"
            member.earns(extraPoints).statusPoints()
        then: "he may or may not be upgraded to a new status"
            member.getStatus() == expectedStatus
        where:
            initialStatus | initialPoints | extraPoints | expectedStatus
            BRONZE        | 0             | 299         | BRONZE
            BRONZE        | 0             | 300         | SILVER
            SILVER        | 0             | 699         | SILVER
            SILVER        | 0             | 700         | GOLD
            GOLD          | 0             | 1499        | GOLD
            GOLD          | 0             | 1500        | PLATINUM
    }

}
