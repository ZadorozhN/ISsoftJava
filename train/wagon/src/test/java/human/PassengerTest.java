package human;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    public static final int ILLEGAL_TICKET_ID = -1;
    public static final int LEGAL_TICKET_ID = 0;
    public static final String LEGAL_ID = UUID.randomUUID().toString();
    public static final String ILLEGAL_ID = null;
    public static final String LEGAL_NAME = "Nikita";
    public static final String ILLEGAL_NAME = null;
    public static final String LEGAL_LASTNAME = "Zadorozhny";
    public static final String ILLEGAL_LASTNAME = null;
    public static final int LEGAL_AGE = 19;
    public static final int ILLEGAL_OLD_AGE = 190;
    public static final int ILLEGAL_NEGATIVE_AGE = -1;

    @Test
    void createPassengerWithNullIdTest() {
        assertThrows(NullPointerException.class,
                () -> Passenger.builder().ticketId(LEGAL_TICKET_ID).id(ILLEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createPassengerWithNullNameTest() {
        assertThrows(NullPointerException.class,
                () -> Passenger.builder().ticketId(LEGAL_TICKET_ID).id(LEGAL_ID)
                        .name(ILLEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createPassengerWithNullLastNameTest() {
        assertThrows(NullPointerException.class,
                () -> Passenger.builder().ticketId(LEGAL_TICKET_ID).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(ILLEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createTooOldPassengerTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Passenger.builder().ticketId(LEGAL_TICKET_ID).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(ILLEGAL_OLD_AGE).build());
    }

    @Test
    void createPassengerWithNegativeAgeTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Passenger.builder().ticketId(LEGAL_TICKET_ID).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(ILLEGAL_NEGATIVE_AGE).build());
    }

    @Test
    void createPassengerTest() {
        assertDoesNotThrow(() -> Passenger.builder().ticketId(LEGAL_TICKET_ID).id(LEGAL_ID)
                .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }

    @Test
    void createPassengerWithIllegalTicketTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Passenger.builder().ticketId(ILLEGAL_TICKET_ID).id(LEGAL_ID)
                        .name(LEGAL_NAME).lastName(LEGAL_LASTNAME).age(LEGAL_AGE).build());
    }
}