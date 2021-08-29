package edu.epam.parsing.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Deposit {
    private String name;
    private String country;
    private String depositor;
    private String accountId;
    private int amountOnDeposit;
    private int profitability;
    private YearMonth timeConstraints;
    private DepositType type;

    public Deposit(){

    }

    public Deposit(String name, String country, String depositor, String accountId, int amountOnDeposit, int profitability, YearMonth timeConstraints, DepositType type){
        this.name = name;
        this.country = country;
        this.depositor = depositor;
        this.accountId = accountId;
        this.amountOnDeposit = amountOnDeposit;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getDepositor(){
        return depositor;
    }

    public void setDepositor(String depositor){
        this.depositor = depositor;
    }

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public int getAmountOnDeposit(){
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(int amountOnDeposit){
        this.amountOnDeposit = amountOnDeposit;
    }

    public int getProfitability(){
        return profitability;
    }

    public void setProfitability(int profitability){
        this.profitability = profitability;
    }

    public YearMonth getTimeConstraints(){
        return timeConstraints;
    }

    public void setTimeConstraints(YearMonth timeConstraints){
        this.timeConstraints = timeConstraints;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Depositor{");
        builder.append("name = ").append(name).append(" ");
        builder.append("country = ").append(country).append(" ");
        builder.append("depositor = ").append(depositor).append(" ");
        builder.append("accountId = ").append(accountId).append(" ");
        builder.append("amountOnDeposit = ").append(amountOnDeposit).append(" ");
        builder.append("profitability = ").append(profitability).append(" ");
        builder.append("timeConstraints = ").append(timeConstraints).append(" ");
        builder.append("type = ").append(type).append(" ");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deposit deposit = (Deposit) o;
        return amountOnDeposit == deposit.amountOnDeposit &&
                profitability == deposit.profitability &&
                name.equals(deposit.name) &&
                country.equals(deposit.country)  &&
                depositor.equals(deposit.depositor)  &&
                accountId.equals(deposit.accountId)  &&
                timeConstraints.equals(deposit.timeConstraints) &&
                type == deposit.type;
    }

    @Override
    public int hashCode() {
        return name.hashCode()
                + country.hashCode()
                + depositor.hashCode()
                + accountId.hashCode()
                + timeConstraints.hashCode()
                + type.hashCode()
                + Integer.hashCode(profitability)
                + Integer.hashCode(amountOnDeposit);
    }
}