package model;

import human.Driver;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class Locomotive extends Wagon {
    private Driver driver;

    public Locomotive(String id) {
        super(id);

        log.info("Locomotive has been created successfully");
    }

    public Locomotive(Driver driver, String id){
        super(id);
        checkNotNull(driver);
        this.driver = driver;

        log.info("Locomotive has been created successfully");
    }

    public static Locomotive of(String id){
        return new Locomotive(id);
    }

    public static Locomotive of(Driver driver, String id){
        return new Locomotive(driver, id);
    }

    public void setDriver(Driver driver){
        checkNotNull(driver);
        this.driver = driver;

        log.info("Driver has been set");
    }

    public void removeDriver(){
        this.driver = null;

        log.info("Driver has been removed");
    }
}
