package human;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    public static final int LEGAL_BIG_EXPERIENCE = 30;
    public static final int LEGAL_EXPERIENCE = 5;
    public static final int ILLEGAL_EXPERIENCE = -1;
    public static final String LEGAL_ID = UUID.randomUUID().toString();
    public static final String ILLEGAL_ID = null;
    public static final String LEGAL_NAME = "Nikita";
    public static final String ILLEGAL_NAME = null;
    public static final String LEGAL_LASTNAME = "Zadorozhny";
    public static final String ILLEGAL_LASTNAME = null;
    public static final int LEGAL_YOUNG_AGE = 19;
    public static final int ILLEGAL_NEGATIVE_AGE = -1;
    public static final int LEGAL_OLD_AGE = 60;
    public static final int ILLEGAL_OLD_AGE = 199;
    public static final int LEGAL_AGE = 30;

    @Test
    void createDriverWithNullIdTest() {
        assertThrows(NullPointerException.class,
                () -> Driver.builder().experience(LEGAL_EXPERIENCE).id(ILLEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createDriverWithNullNameTest() {
        assertThrows(NullPointerException.class,
                () -> Driver.builder().experience(LEGAL_EXPERIENCE).id(LEGAL_ID)
                        .name(ILLEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createDriverWithNullLastNameTest() {
        assertThrows(NullPointerException.class,
                () -> Driver.builder().experience(LEGAL_EXPERIENCE).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(ILLEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createTooOldDriverTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Driver.builder().experience(LEGAL_EXPERIENCE).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(ILLEGAL_OLD_AGE).build());
    }

    @Test
    void createDriverWithNegativeAgeTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Driver.builder().experience(LEGAL_EXPERIENCE).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(ILLEGAL_NEGATIVE_AGE).build());
    }

    @Test
    void createDriverWithInvalidExperienceTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Driver.builder().experience(LEGAL_BIG_EXPERIENCE).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_YOUNG_AGE).build());
    }

    @Test
    void createDriverWithNegativeExperienceTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Driver.builder().experience(ILLEGAL_EXPERIENCE).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createDriverTest() {
        assertDoesNotThrow(() -> Driver.builder().experience(LEGAL_EXPERIENCE).id(LEGAL_ID)
                .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }
}