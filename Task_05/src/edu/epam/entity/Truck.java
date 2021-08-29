package edu.epam.entity;

import edu.epam.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Truck extends Thread {
    static final Logger logger = LogManager.getLogger();
    private long id;
    private RequiredAction action;
    private boolean perishable;

    public Truck(RequiredAction action, boolean perishable){
        this.id = IdGenerator.generateId();
        this.action = action;
        this.perishable = perishable;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public RequiredAction getAction() {
        return action;
    }

    public void setAction(RequiredAction action) {
        this.action = action;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    @Override
    public void run(){
        LogisticsBase base = LogisticsBase.getInstance();
        Terminal terminal = base.takeTerminal(perishable);
        logger.info("Terminal " + terminal.getId() + " was occupied");
        terminal.operation(this);
        base.putTerminal(terminal);
        logger.info("Terminal " + terminal.getId() + " was released");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return id == truck.id && perishable == truck.perishable && action == truck.action;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + Boolean.hashCode(perishable) + action.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Truck{").append(" ");
        builder.append("id: ").append(id).append(" ");
        builder.append("perishable: ").append(perishable).append(" ");
        builder.append("action: ").append(action).append(" }");
        return builder.toString();
    }
}
