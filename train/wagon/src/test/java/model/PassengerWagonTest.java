package model;

import human.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PassengerWagonTest {
    public static final String LEGAL_ID = UUID.randomUUID().toString();
    public static final String ILLEGAL_ID = null;
    public static final int ILLEGAL_CAPACITY = -1;
    public static final int LEGAL_CAPACITY = 10;
    public static final int LEGAL_TICKET_ID = 1;
    public static final String LEGAL_PASSENGER_ID = UUID.randomUUID().toString();
    public static final String LEGAL_PASSENGER_NAME = "Nikita";
    public static final String LEGAL_PASSENGER_LASTNAME = "Zadorozhny";
    public static final int LEGAL_PASSENGER_AGE = 19;
    public static final Passenger LEGAL_PASSENGER = Passenger.builder().ticketId(LEGAL_TICKET_ID)
            .id(LEGAL_PASSENGER_ID).name(LEGAL_PASSENGER_NAME).lastName(LEGAL_PASSENGER_LASTNAME)
            .age(LEGAL_PASSENGER_AGE).build();

    @Test
    void createPassengerWagonWithIllegalCapacityTest() {
        assertThrows(IllegalArgumentException.class,
                () -> PassengerWagon.builder().capacity(ILLEGAL_CAPACITY).id(LEGAL_ID).build());
    }

    @Test
    void createPassengerWagonWithNullIdTest() {
        assertThrows(NullPointerException.class,
                () -> PassengerWagon.builder().capacity(LEGAL_CAPACITY).id(ILLEGAL_ID).build());
    }

    @Test
    void createPassengerWagonTest() {
        assertDoesNotThrow(() -> PassengerWagon.builder().capacity(LEGAL_CAPACITY).id(LEGAL_ID).build());
    }

    @Test
    void addPassengerTest() {
        PassengerWagon passengerWagon = PassengerWagon.builder().id(LEGAL_ID).capacity(LEGAL_CAPACITY).build();
        assertDoesNotThrow(() -> passengerWagon.addPassenger(LEGAL_PASSENGER));
    }

    @Test
    void addTwoPassengerWithTheSameTicketIdTest() {
        PassengerWagon passengerWagon = PassengerWagon.builder().id(LEGAL_ID).capacity(LEGAL_CAPACITY).build();
        passengerWagon.addPassenger(LEGAL_PASSENGER);
        Passenger secondPassengerWithTheSameTickedId = LEGAL_PASSENGER.toBuilder().build();
        assertThrows(IllegalArgumentException.class,
                () -> passengerWagon.addPassenger(secondPassengerWithTheSameTickedId));
    }

    @Test
    void addNullPassengerTest() {
        PassengerWagon passengerWagon = PassengerWagon.builder().id(LEGAL_ID).capacity(LEGAL_CAPACITY).build();
        Passenger illegalPassenger = null;
        assertThrows(NullPointerException.class,
                () -> passengerWagon.addPassenger(illegalPassenger));
    }

    @Test
    void addPassengerWithIllegalTicketIdTest() {
        PassengerWagon passengerWagon = PassengerWagon.builder().id(LEGAL_ID).capacity(LEGAL_CAPACITY).build();
        int illegalTickedIdForThisWagon = 20;
        Passenger passengerWithIllegalTicket = LEGAL_PASSENGER.toBuilder()
                .ticketId(illegalTickedIdForThisWagon).build();
        assertThrows(IllegalArgumentException.class,
                () -> passengerWagon.addPassenger(passengerWithIllegalTicket));
    }

    @Test
    void removePassengerTest(){
        PassengerWagon passengerWagon = PassengerWagon.builder().id(LEGAL_ID).capacity(LEGAL_CAPACITY).build();
        passengerWagon.addPassenger(LEGAL_PASSENGER);
        assertDoesNotThrow(() -> passengerWagon.removePassenger(LEGAL_PASSENGER.getTicketId()));
    }

    @Test
    void removePassengerFromEmptyWagonTest(){
        PassengerWagon passengerWagon = PassengerWagon.builder().id(LEGAL_ID).capacity(LEGAL_CAPACITY).build();
        assertDoesNotThrow(() -> passengerWagon.removePassenger(LEGAL_TICKET_ID));
    }
}