package com.pizzisalle.service;

import com.pizzisalle.constants.Delegations;
import java.util.Random;

/**
 * PATTERN: Singleton - Ensures only one instance manages delegation assignments
 */
public class DelegationManager {
    private static DelegationManager instance;
    private final Random random = new Random();

    private DelegationManager() {}

    public static DelegationManager getInstance() {
        if (instance == null) {
            instance = new DelegationManager();
        }
        return instance;
    }

    public Delegations assignRandomDelegation() {
        Delegations[] delegations = Delegations.values();
        return delegations[random.nextInt(delegations.length)];
    }

    public String getWelcomeMessage(Delegations delegation) {
        return String.format("\nWelcome to PizziSalle! Your order will be handled by our %s delegation.\n",
                           delegation.getName());
    }

    public boolean isDelegationValid(Delegations delegation) {
        Delegations[] values = Delegations.values();
        for (Delegations d : values) {
            if (d.equals(delegation)) {
                return true;
            }
        }
        return false;
    }
}