package com.bddinaction.chapter12.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"json:target/cucumber/earning-points.json"},
                  tags = {"@earning-points"})
public class EarningPoints {}
