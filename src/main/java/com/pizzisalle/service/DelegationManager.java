package com.pizzisalle.service;

import java.util.Random;
import java.util.List;
import java.util.Arrays;

/**
 * Represents the available delegations in the system.
 */
public enum Delegations {
    BARCELONA,
    GIRONA,
    LLEIDA,
    TARRAGONA
}

// PATTERN: Singleton Pattern - Used to ensure there is only one instance of DelegationManager
// throughout the application, providing a global point of access to delegation assignment.
// This pattern is crucial here because:
// 1. We need centralized control over delegation management
// 2. All parts of the system should work with the same delegation data
// 3. It prevents multiple instances from potentially assigning different delegations
// 4. It provides a global access point to delegation operations
// 5. It ensures consistency in delegation validation across the application
public class DelegationManager {
    private static volatile DelegationManager instance;
    private final Random random;
    private final List<Delegations> delegations;

    private DelegationManager() {
        random = new Random();
        delegations = Arrays.asList(Delegations.values());
    }

    public static DelegationManager getInstance() {
        if (instance == null) {
            synchronized (DelegationManager.class) {
                // Double-check locking for thread safety
                if (instance == null) {
                    instance = new DelegationManager();
                }
            }
        }
        return instance;
    }

    public Delegations assignRandomDelegation() {
        return delegations.get(random.nextInt(delegations.size()));
    }

    public boolean isDelegationValid(Delegations delegation) {
        return delegations.contains(delegation);
    }
}