package com.pizzisalle.constants;

public enum CrustType {
    ORIGINAL(0),
    THIN(100),
    SICILIAN(200);

    private final int extraCostInCents;

    CrustType(int extraCostInCents) {
        this.extraCostInCents = extraCostInCents;
    }

    public int getExtraCostInCents() {
        return extraCostInCents;
    }

    public String getFormattedExtraCost() {
        return String.format("€%.2f", extraCostInCents / 100.0);
    }
}