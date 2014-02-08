package com.bddinaction.chapter12.model;


import com.google.common.collect.ImmutableList;

import java.util.List;

public class FrequentFlyer {

    private final String frequentFlyerNumber;
    private final String firstName;
    private final String lastName;
    private FrequentFlyerStatus status;
    private int statusPoints;

    private FrequentFlyer(String frequentFlyerNumber, String firstName, String lastName,
                          FrequentFlyerStatus status, int statusPoints) {
        this.frequentFlyerNumber = frequentFlyerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.statusPoints = statusPoints;
    }

    private FrequentFlyer(String frequentFlyerNumber, String firstName, String lastName) {
        this(frequentFlyerNumber, firstName, lastName, FrequentFlyerStatus.BRONZE, 0);
    }

    public FrequentFlyer withStatus(FrequentFlyerStatus newStatus) {
        this.setStatus(newStatus);
        return this;
    }

    public String getFrequentFlyerNumber() {
        return frequentFlyerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static FrequentFlyerBuilder withFrequentFlyerNumber(String frequentFlyerNumber) {
        return new FrequentFlyerBuilder(frequentFlyerNumber);
    }

    public FrequentFlyerStatus getStatus() {
        return status;
    }

    public FrequentFlyer withStatusPoints(int statusPoints) {
        this.setStatusPoints(statusPoints);
        return this;
    }

    public void setStatus(FrequentFlyerStatus status) {
        this.status = status;
    }

    public void setStatusPoints(int statusPoints) {
        this.statusPoints = statusPoints;
        updateStatusLevel();
    }

    private void updateStatusLevel() {
        setStatus(FrequentFlyerStatus.statusLevelFor(statusPoints));
    }

    public int getStatusPoints() { return statusPoints; }

    public List<FrequentFlyerStatus> getUnachievedStatuses() {
        return ImmutableList.of(FrequentFlyerStatus.GOLD, FrequentFlyerStatus.PLATINUM);
    }

    public static class FrequentFlyerBuilder {

        private String frequentFlyerNumber;

        public FrequentFlyerBuilder(String frequentFlyerNumber) {
            this.frequentFlyerNumber = frequentFlyerNumber;
        }

        public FrequentFlyer named(String firstName, String lastName) {
            return new FrequentFlyer(frequentFlyerNumber, firstName, lastName);
        }
    }

    public PointEarner earns(int points) {
        return new PointEarner(this, points);
    }

    private void addStatusPoints(int extraPoints) {
        setStatusPoints(getStatusPoints() + extraPoints);
    }

    public static class PointEarner {
        private final int points;
        private final FrequentFlyer frequentFlyer;

        public PointEarner(FrequentFlyer frequentFlyer, int points) {
            this.frequentFlyer = frequentFlyer;
            this.points = points;
        }

        public void statusPoints() {
            frequentFlyer.addStatusPoints(points);
        }
    }

}
