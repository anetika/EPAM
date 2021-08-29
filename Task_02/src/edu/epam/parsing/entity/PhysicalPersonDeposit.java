package edu.epam.parsing.entity;

import java.time.YearMonth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhysicalPersonDeposit extends Deposit{
    static Logger logger = LogManager.getLogger();
    private int passportNumber;

    public PhysicalPersonDeposit(){
        super();
        logger.info("PhysicalPersonDeposit was created");
    }

    public PhysicalPersonDeposit(String name, String country, String depositor, String accountId, int amountOnDeposit, int profitability, YearMonth timeConstraints, int passportNumber, DepositType type){
        super(name, country, depositor, accountId, amountOnDeposit, profitability, timeConstraints, type);
        this.passportNumber = passportNumber;
        logger.info("PhysicalPersonDeposit was created");
    }

    public int getPassportNumber(){
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber){
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.deleteCharAt(builder.length() - 1);
        builder.append("passportNumber = ").append(passportNumber).append(" ");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PhysicalPersonDeposit that = (PhysicalPersonDeposit) o;
        return passportNumber == that.passportNumber;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Integer.hashCode(passportNumber);
    }

}
