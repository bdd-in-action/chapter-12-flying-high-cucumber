package com.bddinaction.chapter12.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"json:target/cucumber/viewing-points.json"}, tags = {"@viewing-flights"})
public class ViewingFlights {
}
