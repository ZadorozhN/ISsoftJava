package model;

import com.google.common.collect.ImmutableList;
import human.Passenger;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class PassengerWagon extends Wagon {
    public static final int MIN_CAPACITY = 0;

    private final List<Passenger> passengers;
    private final int capacity;

    @Builder
    public PassengerWagon(int capacity, String id) {
        super(id);
        checkArgument(capacity >= MIN_CAPACITY);
        this.capacity = capacity;
        passengers = new ArrayList<>(capacity);

        log.info("Passenger wagon has been created successfully");
    }

    public void addPassenger(Passenger passenger){
        checkNotNull(passenger);
        checkArgument(passenger.getTicketId() < capacity);
        checkArgument(!passengers.stream().mapToInt(p -> p.getTicketId()).anyMatch(t -> t == passenger.getTicketId()));
        passengers.add(passenger);

        log.info("Passenger has been added into the wagon successfully");
    }

    public void removePassenger(int tickedId){
        checkArgument(tickedId > Passenger.MIN_TICKET_ID);
        for(int i = 0; i < passengers.size(); i++){
            if(passengers.get(i).getTicketId() == tickedId){
                passengers.remove(passengers.get(i));
                log.info("User has been removed");
                return;
            }
        }

        log.info("User hasn't been found");
    }

    public Passenger getPassenger(int tickedId){
        for(int i = 0; i < passengers.size(); i++){
            if(passengers.get(i).getTicketId() == tickedId){
                log.info("User has been found");
                return passengers.get(i);
            }
        }

        log.info("User hasn't been found");
        return null;
    }

    public Passenger getPassenger(String id){
        checkNotNull(id);
        for(int i = 0; i < passengers.size(); i++){
            if(passengers.get(i).getId() == id){
                log.info("User has been found");
                return passengers.get(i);
            }
        }

        log.info("User hasn't been found");
        return null;
    }

    public ImmutableList<Passenger> getPassengers(){
        return new ImmutableList.Builder<Passenger>().addAll(passengers).build();
    }
}
