package com.pizzisalle.constants;

public enum CrustType {
    ORIGINAL(0.0),
    THIN(1.0),
    SICILIAN(2.0);

    private final double extraCost;

    CrustType(double extraCost) {
        this.extraCost = extraCost;
    }

    public double getExtraCost() {
        return extraCost;
    }
}