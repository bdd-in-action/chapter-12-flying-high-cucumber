package com.bddinaction.chapter12.junit;

import com.bddinaction.chapter12.model.FrequentFlyer;
import com.bddinaction.chapter12.model.FrequentFlyerStatus;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class WhenCheckingMinimumStatusPoints {

    @Test
    public void should_need_300_points_for_silver() {
        FrequentFlyerStatus status = FrequentFlyerStatus.statusLevelFor(300);
        assertThat(status, is(FrequentFlyerStatus.SILVER));
    }

    FrequentFlyer member;

    @Before
    public void newFrequentFlyer() {
        member = FrequentFlyer.withFrequentFlyerNumber("12345678").named("Joe", "Bloggs");
    }

    @Test
    public void should_have_bronze_status_initially() {
        assertEquals(FrequentFlyerStatus.BRONZE, member.getStatus());

        assertThat(member.getStatus(), is(FrequentFlyerStatus.BRONZE));
    }

    @Test
    public void should_cumulate_points_with_each_flight() {

        FrequentFlyer member = FrequentFlyer.withFrequentFlyerNumber("12345678").named("Joe", "Bloggs");

        member.earns(100).statusPoints();
        member.earns(200).statusPoints();

        assertThat(member.getStatusPoints(),is(greaterThanOrEqualTo((300))));
        assertThat(member.getStatus(), is(FrequentFlyerStatus.SILVER));
    }

    @Test
    public void should_see_next_obtainable_statuses() {
        assertThat(member.getUnachievedStatuses(), hasItems(FrequentFlyerStatus.GOLD, FrequentFlyerStatus.PLATINUM));


        List<Integer> memberAges = ImmutableList.of(21, 30, 40);

        assertThat(memberAges,everyItem(greaterThanOrEqualTo(18)));
    }
}
