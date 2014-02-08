package com.bddinaction.chapter12.model;

public enum FrequentFlyerStatus {

    BRONZE(0), SILVER(300), GOLD(700), PLATINUM(1500);

    private final int minimumPoints;

    FrequentFlyerStatus(int minimumPoints) {
        this.minimumPoints = minimumPoints;
    }

    public static FrequentFlyerStatus statusLevelFor(int statusPoints) {
        FrequentFlyerStatus highestMatchingStatus = BRONZE;
        for(FrequentFlyerStatus status : FrequentFlyerStatus.values())  {
            if (statusPoints >= status.minimumPoints) {
                highestMatchingStatus = status;
            }
        }
        return highestMatchingStatus;
    }
}
