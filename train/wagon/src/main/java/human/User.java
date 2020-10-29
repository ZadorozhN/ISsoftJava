package human;

import item.Cargo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Getter
public abstract class User {
    public final static int MAX_AGE = 120;
    public final static int MIN_AGE = 0;

    protected final String id;
    protected final String name;
    protected final String lastName;
    protected final int age;

    protected User(String id, String name, String lastName, int age){
        checkNotNull(id);
        checkNotNull(name);
        checkNotNull(lastName);
        checkArgument(age > MIN_AGE && age < MAX_AGE);
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;

        log.info("User has been created successfully");
    }
}