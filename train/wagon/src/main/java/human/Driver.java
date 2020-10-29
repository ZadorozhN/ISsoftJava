package human;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Getter
public class Driver extends User {
    public static final int WORKING_AGE = 18;
    public static final int MIN_EXPERIENCE = 0;

    private int experience;

    @Builder
    public Driver(int experience, String id, String name, String lastName, int age) {
        super(id, name, lastName, age);
        checkArgument(experience >= MIN_EXPERIENCE && experience < age - WORKING_AGE);
        this.experience = experience;

        log.info("Driver has been created successfully");
    }
}
