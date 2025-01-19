package com.pizzisalle.constants;

public enum CrustType {
    ORIGINAL(0),     // €0.00
    THIN(100),       // €1.00
    SICILIAN(200);   // €2.00

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