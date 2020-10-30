import human.Driver;
import human.Passenger;
import item.Cargo;
import lombok.extern.slf4j.Slf4j;
import model.CargoWagon;
import model.Locomotive;
import model.PassengerWagon;
import model.Wagon;
import java.util.UUID;

@Slf4j
public class Application {
    public static void main(String[] args){
        Driver driver = Driver.builder().experience(1).age(20).name("Nikita").lastName("Zadorozhny")
                .id(UUID.randomUUID().toString()).build();
        Locomotive locomotive = Locomotive.of(UUID.randomUUID().toString());
        locomotive.setDriver(driver);

        Passenger firstPassenger = Passenger.builder().ticketId(12).age(12).name("Dmitriy").lastName("Pinchuk")
                .id(UUID.randomUUID().toString()).build();
        Passenger secondPassenger = Passenger.builder().ticketId(13).age(13).name("Ivan").lastName("Pavlovich")
                .id(UUID.randomUUID().toString()).build();
        Passenger thirdPassenger = Passenger.builder().ticketId(14).age(14).name("Maxim").lastName("Pernach")
                .id(UUID.randomUUID().toString()).build();
        PassengerWagon passengerWagon = PassengerWagon.builder().capacity(40).id(UUID.randomUUID().toString()).build();

        passengerWagon.addPassenger(firstPassenger);
        passengerWagon.addPassenger(secondPassenger);
        passengerWagon.addPassenger(thirdPassenger);

        locomotive.setPreviousWagon(passengerWagon);

        CargoWagon cargoWagon = CargoWagon.builder().carryingCapacity(40).id(UUID.randomUUID().toString()).build();
        Cargo cargo = Cargo.builder().id(UUID.randomUUID().toString()).name("Glass").weight(20).build();
        cargoWagon.addCargo(cargo);
        passengerWagon.setPreviousWagon(cargoWagon);

        Wagon wagon = locomotive;
        while(wagon != null){
            log.info(wagon.getId());
            wagon = wagon.getPreviousWagon();
        }
    }
}
