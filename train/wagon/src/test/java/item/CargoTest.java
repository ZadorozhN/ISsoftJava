package item;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {
    public static final String LEGAL_ID = UUID.randomUUID().toString();
    public static final String ILLEGAL_ID = null;
    public static final String LEGAL_NAME = "Glass";
    public static final String ILLEGAL_NAME = null;
    public static final int LEGAL_WEIGHT = 10;
    public static final int ILLEGAL_WEIGHT = -1;

    @Test
    void createCargoTest() {
        assertDoesNotThrow(() -> Cargo.builder().id(LEGAL_ID).name(LEGAL_NAME).weight(LEGAL_WEIGHT).build());
    }

    @Test
    void createCargoWithIllegalWightTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Cargo.builder().id(LEGAL_ID).name(LEGAL_NAME).weight(ILLEGAL_WEIGHT).build());
    }

    @Test
    void createCargoWithIllegalNameTest() {
        assertThrows(NullPointerException.class,
                () -> Cargo.builder().id(LEGAL_ID).name(ILLEGAL_NAME).weight(LEGAL_WEIGHT).build());
    }

    @Test
    void createCargoWithIllegalIdTest() {
        assertThrows(NullPointerException.class,
                () -> Cargo.builder().id(ILLEGAL_ID).name(LEGAL_NAME).weight(LEGAL_WEIGHT).build());
    }
}