package edu.epam.entity;

import edu.epam.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Terminal {
    static final Logger logger = LogManager.getLogger();
    private final long id;
    private static final int MIN_WAITING_TIME = 50;
    private static final int MAX_WAITING_TIME = 100;

    public Terminal(){
        this.id = IdGenerator.generateId();
    }

    public long getId(){
        return id;
    }

    public void operation(Truck truck){
        long id = truck.getId();
        logger.info("Terminal " + getId() + " serves truck");
        int waitingTime = new Random().nextInt(MAX_WAITING_TIME - MIN_WAITING_TIME) + MIN_WAITING_TIME;
        try {
            if (truck.getAction() == RequiredAction.LOAD){
                TimeUnit.MILLISECONDS.sleep(waitingTime);
            } else {
                TimeUnit.MILLISECONDS.sleep(waitingTime);
            }
        } catch (InterruptedException e) {
            logger.error("Exception " + e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Terminal terminal = (Terminal) o;
        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
