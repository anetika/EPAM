package edu.epam.parsing.parser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.exception.DepositException;

import java.util.HashSet;
import java.util.Set;

public abstract class DepositBuilder {
    protected Set<Deposit> deposits;

    public DepositBuilder(){
        deposits = new HashSet<Deposit>();
    }

    public DepositBuilder(Set<Deposit> deposits){
        this.deposits = deposits;
    }

    public Set<Deposit> getDeposits(){
        return deposits;
    }

    public abstract void buildSetDeposits(String filename) throws DepositException;
}
