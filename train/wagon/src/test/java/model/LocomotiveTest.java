package model;

import human.Driver;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocomotiveTest {
    public static final int LEGAL_DRIVER_EXPERIENCE = 5;
    public static final String LEGAL_DRIVER_ID = UUID.randomUUID().toString();
    public static final String LEGAL_DRIVER_NAME = "Nikita";
    public static final String LEGAL_DRIVER_LASTNAME = "Zadorozhny";
    public static final int LEGAL_DRIVER_AGE = 30;
    public static final Driver LEGAL_DRIVER = Driver.builder().experience(LEGAL_DRIVER_EXPERIENCE).id(LEGAL_DRIVER_ID)
        .name(LEGAL_DRIVER_NAME).lastName(LEGAL_DRIVER_LASTNAME).age(LEGAL_DRIVER_AGE).build();
    public static final Driver ILLEGAL_DRIVER = null;
    public static final String LEGAL_WAGON_ID = UUID.randomUUID().toString();
    public static final String ILLEGAL_WAGON_ID = null;

    @Test
    void createLocomotiveTest() {
        assertDoesNotThrow(() -> Locomotive.of(LEGAL_WAGON_ID));
    }

    @Test
    void createLocomotiveWithNullIdTest() {
        assertThrows(NullPointerException.class,
                () -> Locomotive.of(ILLEGAL_WAGON_ID));
    }

    @Test
    void createLocomotiveWithDriverTest() {
        assertDoesNotThrow(() -> Locomotive.of(LEGAL_DRIVER, LEGAL_WAGON_ID));
    }

    @Test
    void createLocomotiveWithIllegalDriverTest() {
        assertThrows(NullPointerException.class,
                () -> Locomotive.of(ILLEGAL_DRIVER, LEGAL_WAGON_ID));
    }

    @Test
    void setIllegalDriverTest() {
        Locomotive locomotive = Locomotive.of(LEGAL_WAGON_ID);
        assertThrows(NullPointerException.class,
                () -> locomotive.setDriver(ILLEGAL_DRIVER));
    }

    @Test
    void setLegalDriverTest() {
        Locomotive locomotive = Locomotive.of(LEGAL_WAGON_ID);
        assertDoesNotThrow(() -> locomotive.setDriver(LEGAL_DRIVER));
    }

    @Test
    void removeDriverTest() {
        Locomotive locomotive = Locomotive.of(LEGAL_DRIVER, LEGAL_WAGON_ID);
        assertDoesNotThrow(() -> locomotive.removeDriver());
    }

    @Test
    void removeNullDriverTest() {
        Locomotive locomotive = Locomotive.of(LEGAL_WAGON_ID);
        assertDoesNotThrow(() -> locomotive.removeDriver());
    }
}