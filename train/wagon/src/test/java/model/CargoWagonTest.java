package model;

import item.Cargo;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CargoWagonTest {
    public static final int LEGAL_CAPACITY = 10;
    public static final int ILLEGAL_CAPACITY = -1;
    public static final String LEGAL_ID = UUID.randomUUID().toString();
    public static final String ILLEGAL_ID = null;
    public static final String LEGAL_CARGO_ID = UUID.randomUUID().toString();
    public static final String LEGAL_CARGO_NAME = "Glass";
    public static final int LEGAL_WEIGHT = 5;
    public static final int LEGAL_TOO_HEAVY_WEIGHT = 50;
    public static final Cargo LEGAL_CARGO = Cargo.builder().id(LEGAL_CARGO_ID)
            .name(LEGAL_CARGO_NAME).weight(LEGAL_WEIGHT).build();

    @Test
    void createCargoWagonTest() {
        assertDoesNotThrow(() -> CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build());
    }

    @Test
    void createCargoWagonWithIllegalIdTest() {
        assertThrows(NullPointerException.class,
                () -> CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(ILLEGAL_ID).build());
    }

    @Test
    void createCargoWagonWithIllegalCapacityTest() {
        assertThrows(IllegalArgumentException.class,
                () -> CargoWagon.builder().carryingCapacity(ILLEGAL_CAPACITY).id(LEGAL_ID).build());
    }

    @Test
    void addCargoTest() {
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        assertDoesNotThrow(() -> cargoWagon.addCargo(LEGAL_CARGO));
    }

    @Test
    void addNullCargoTest() {
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        Cargo illegalCargo = null;
        assertThrows(NullPointerException.class,
                () -> cargoWagon.addCargo(illegalCargo));
    }

    @Test
    void addTooHeavyCargoTest() {
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        Cargo tooHeavyCargo = LEGAL_CARGO.toBuilder().weight(LEGAL_TOO_HEAVY_WEIGHT).build();
        assertThrows(IllegalArgumentException.class,
                () -> cargoWagon.addCargo(tooHeavyCargo));
    }

    @Test
    void removeCargoByNullIdTest() {
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        String illegalId = null;
        assertThrows(NullPointerException.class,
                () -> cargoWagon.removeCargo(illegalId));
    }

    @Test
    void removeCargoFromEmptyWagon() {
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        assertDoesNotThrow(() -> cargoWagon.removeCargo(LEGAL_CARGO_ID));
    }

    @Test
    void removeCargoTest() {
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        cargoWagon.addCargo(LEGAL_CARGO);
        assertDoesNotThrow(() -> cargoWagon.removeCargo(LEGAL_CARGO.getId()));
    }

    @Test
    void connectNextWagon(){
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        String secondCargoWagonId = UUID.randomUUID().toString();
        CargoWagon secondCargoWagon = cargoWagon.toBuilder().id(secondCargoWagonId).build();
        assertDoesNotThrow(() -> cargoWagon.setNextWagon(secondCargoWagon));
        assertEquals(secondCargoWagon.previousWagon, cargoWagon);
    }

    @Test
    void connectPreviousWagon(){
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        String secondCargoWagonId = UUID.randomUUID().toString();
        CargoWagon secondCargoWagon = cargoWagon.toBuilder().id(secondCargoWagonId).build();
        assertDoesNotThrow(() -> cargoWagon.setPreviousWagon(secondCargoWagon));
        assertEquals(secondCargoWagon.nextWagon, cargoWagon);
    }

    @Test
    void connectNextNullWagon(){
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        CargoWagon secondCargoWagon = null;
        assertThrows(NullPointerException.class,
                () -> cargoWagon.setNextWagon(secondCargoWagon));
    }

    @Test
    void connectPreviousNullWagon(){
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        CargoWagon secondCargoWagon = null;
        assertThrows(NullPointerException.class,
                () -> cargoWagon.setPreviousWagon(secondCargoWagon));
    }

    @Test
    void disconnectNextWagon(){
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        String secondCargoWagonId = UUID.randomUUID().toString();
        CargoWagon secondCargoWagon = cargoWagon.toBuilder().id(secondCargoWagonId).build();
        cargoWagon.setNextWagon(secondCargoWagon);
        assertDoesNotThrow(() -> cargoWagon.disconnectNextWagon());
        assertNull(secondCargoWagon.previousWagon);
        assertNull(cargoWagon.nextWagon);
    }

    @Test
    void disconnectPreviousWagon(){
        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(LEGAL_CAPACITY).id(LEGAL_ID).build();
        String secondCargoWagonId = UUID.randomUUID().toString();
        CargoWagon secondCargoWagon = cargoWagon.toBuilder().id(secondCargoWagonId).build();
        cargoWagon.setPreviousWagon(secondCargoWagon);
        assertDoesNotThrow(() -> cargoWagon.disconnectPreviousWagon());
        assertNull(secondCargoWagon.nextWagon);
        assertNull(cargoWagon.previousWagon);
    }

}