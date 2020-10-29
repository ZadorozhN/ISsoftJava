package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Getter
public abstract class Wagon {
    protected final String id;
    protected Wagon nextWagon;
    protected Wagon previousWagon;

    protected Wagon(String id) {
        checkNotNull(id);
        this.id = id;

        log.info("Wagon has been created successfully");
    }

    public final void setNextWagon(Wagon nextWagon) {
        checkNotNull(nextWagon);
        checkArgument(nextWagon != this);
        if(this.nextWagon != null){
            this.nextWagon.previousWagon = null;
        }
        this.nextWagon = nextWagon;
        nextWagon.previousWagon = this;

        log.info("Connection with next wagon is successful");
    }

    public final void setPreviousWagon(Wagon previousWagon) {
        checkNotNull(previousWagon);
        checkArgument(previousWagon != this);
        if(this.previousWagon != null){
            this.previousWagon.nextWagon = null;
        }
        this.previousWagon = previousWagon;
        previousWagon.nextWagon = this;

        log.info("Connection with previous wagon is successful");
    }

    public final void disconnectNextWagon(){
        if(this.nextWagon != null){
            this.nextWagon.previousWagon = null;
        }
        nextWagon = null;

        log.info("Next wagon has been disconnected");
    }

    public final void disconnectPreviousWagon(){
        if(this.previousWagon != null){
            this.previousWagon.nextWagon = null;
        }
        previousWagon = null;

        log.info("Previous wagon has been disconnected");
    }
}
