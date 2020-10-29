package item;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Getter
@Builder(toBuilder = true)
public class Cargo {
    public static final int MIN_WIGHT = 1;

    private final String id;
    private final String name;
    private final int weight;

    public Cargo(String id, String name, int weight) {
        checkNotNull(id);
        checkNotNull(name);
        checkArgument(weight >= MIN_WIGHT);
        this.id = id;
        this.name = name;
        this.weight = weight;

        log.info("Cargo has been created successfully");
    }
}
