package edu.epam.parsing.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.YearMonth;

public class LegalPersonDeposit extends Deposit{
    static Logger logger = LogManager.getLogger();
    private String nameOfOrganization;

    public LegalPersonDeposit(){
        super();
        logger.info("LegalPersonDeposit was created");
    }

    public LegalPersonDeposit(String name, String country, String depositor, String accountId, int amountOnDeposit, int profitability, YearMonth timeConstraints, String nameOfOrganization, DepositType type){
        super(name, country, depositor, accountId, amountOnDeposit, profitability, timeConstraints, type);
        this.nameOfOrganization = nameOfOrganization;
        logger.info("LegalPersonDeposit was created");
    }

    public String getNameOfOrganization(){
        return nameOfOrganization;
    }

    public void setNameOfOrganization(String nameOfOrganization){
        this.nameOfOrganization = nameOfOrganization;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.deleteCharAt(builder.length() - 1);
        builder.append("nameOfOrganization = ").append(nameOfOrganization).append(" ");
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
        LegalPersonDeposit that = (LegalPersonDeposit) o;
        return nameOfOrganization.equals(that.nameOfOrganization);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + nameOfOrganization.hashCode();
    }
}
