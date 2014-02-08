package com.bddinaction.chapter12.cucumber.steps;

import com.bddinaction.chapter12.cucumber.steps.persona.FrequentFlyerPersona;
import com.bddinaction.chapter12.model.FrequentFlyer;
import com.bddinaction.chapter12.model.FrequentFlyerStatus;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.fest.assertions.Assertions.assertThat;

public class EarningStatus {

    private final FrequentFlyerHelper frequentFlyerHelper;

    public EarningStatus(FrequentFlyerHelper frequentFlyerHelper) {
        this.frequentFlyerHelper = frequentFlyerHelper;
    }

    @Given("^(.*) is a (.*) Frequent Flyer member$")
    public void a_Frequent_Flyer_member(FrequentFlyerPersona persona, FrequentFlyerStatus status) {
        FrequentFlyer member = persona.getFrequentFlyer();
        member.setStatus(status);
        frequentFlyerHelper.setFrequentFlyer(member);
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("^(?:s?)he has (.*) status points$")
    public void earned_status_points(int statusPoints) {
        FrequentFlyer member = frequentFlyerHelper.getFrequentFlyer();
        member.setStatusPoints(statusPoints);
    }

    @When("^(?:s?)he earns (.*) extra status points$")
    public void earn_extra_status_points(int points) {
        FrequentFlyer member = frequentFlyerHelper.getFrequentFlyer();
        member.earns(points).statusPoints();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^(?:s?)he should have a status of (.*)$")
    public void should_have_status_of(FrequentFlyerStatus expectedStatus) {
        FrequentFlyer member = frequentFlyerHelper.getFrequentFlyer();
        assertThat(member.getStatus()).isEqualTo(expectedStatus);
    }

}
