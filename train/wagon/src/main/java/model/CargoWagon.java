package model;

import com.google.common.collect.ImmutableList;
import human.Passenger;
import item.Cargo;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class CargoWagon extends Wagon {
    public final static int MIN_CAPACITY = 0;

    private final int carryingCapacity;
    private int freeSpace;
    private final List<Cargo> cargos;

    @Builder(toBuilder = true)
    public CargoWagon(int carryingCapacity, String id) {
        super(id);
        checkArgument(carryingCapacity >= MIN_CAPACITY);
        this.freeSpace = carryingCapacity;
        this.carryingCapacity = carryingCapacity;
        cargos = new ArrayList<>();

        log.info("Cargo wagon has been created successfully");
    }

    public void addCargo(Cargo cargo){
        checkNotNull(cargo);
        checkArgument(cargo.getWeight() <= freeSpace);
        freeSpace -= cargo.getWeight();
        cargos.add(cargo);

        log.info("Cargo has been added");
    }

    public void removeCargo(String id){
        checkNotNull(id);
        for(int i = 0; i < cargos.size(); i++){
            if(cargos.get(i).getId() == id){
                cargos.remove(cargos.get(i));
                log.info("Cargo has been removed");
                return;
            }
        }

        log.info("Cargo hasn't been found");
    }

    public ImmutableList<Cargo> getCargos(){
        return new ImmutableList.Builder<Cargo>().addAll(cargos).build();
    }
}
