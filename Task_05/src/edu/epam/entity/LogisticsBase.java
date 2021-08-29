package edu.epam.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticsBase {
    static final Logger logger = LogManager.getLogger();
    private static LogisticsBase instance;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Deque<Terminal> terminals = new ArrayDeque<>();
    private final Deque<Condition> trucks = new ArrayDeque<>();
    private static final int TERMINAL_AMOUNT;

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources/LogisticBase");
            TERMINAL_AMOUNT = Integer.parseInt(bundle.getString("TerminalAmount"));
        } catch (MissingResourceException e) {
            logger.error("File not found");
            throw new ExceptionInInitializerError("File not found");
        }
    }

    private LogisticsBase() {
        for (int i = 0; i < TERMINAL_AMOUNT; i++){
            terminals.add(new Terminal());
        }
    }

    public static LogisticsBase getInstance(){
        while (instance == null){
            if (isCreated.compareAndSet(false, true)){
                instance = new LogisticsBase();
            }
        }
        return instance;
    }

    public Terminal takeTerminal(boolean perishable){
        try {
            reentrantLock.lock();
            if (terminals.isEmpty()){
                try {
                    Condition condition = reentrantLock.newCondition();
                    if (perishable) {
                        trucks.addFirst(condition);
                    } else {
                        trucks.addLast(condition);
                    }
                    condition.await();
                } catch (InterruptedException e) {
                    logger.error("Exception" + e);
                    Thread.currentThread().interrupt();
                }
            }
            return terminals.removeFirst();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void putTerminal(Terminal terminal){
        try {
            reentrantLock.lock();
            terminals.addLast(terminal);
            if (!trucks.isEmpty()) {
                Condition condition = trucks.pollFirst();
                condition.signal();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

}
