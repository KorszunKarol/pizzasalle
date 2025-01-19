package com.pizzisalle.service;

import com.pizzisalle.constants.Delegations;
import java.util.Random;

/**
 * PATTERN: Singleton - This class ensures that only one instance of DelegationManager exists
 * throughout the application's lifecycle. This is important because we want to centralize
 * the delegation assignment logic and maintain consistent state.
 */
public class DelegationManager {
    private static DelegationManager instance;
    private final Random random;

    private DelegationManager() {
        random = new Random();
    }

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