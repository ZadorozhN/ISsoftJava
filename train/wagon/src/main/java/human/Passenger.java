package human;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Getter
public class Passenger extends User {
    public static final int MIN_TICKET_ID = 0;

    private final int ticketId;

    @Builder(toBuilder = true)
    public Passenger(int ticketId, String id, String name, String lastName, int age){
        super(id, name, lastName, age);
        checkArgument(ticketId >= MIN_TICKET_ID);
        this.ticketId = ticketId;

        log.info("Passenger has been created successfully");
    }
}
