package de.myrnet.jpavsjdbc;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UuidDataCreationHelper {

    @Test
    public void createUuids() {
        int uuidCount = 10;
        for(int i = 0; i < uuidCount; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

}
