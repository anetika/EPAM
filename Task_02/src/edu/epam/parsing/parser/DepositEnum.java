package edu.epam.parsing.parser;

public enum DepositEnum {
    DEPOSITS("deposits"),
    LEGAL_PERSON_DEPOSIT("legal-person-deposit"),
    PHYSICAL_PERSON_DEPOSIT("physical-person-deposit"),
    NAME("name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    ACCOUNT_ID("account-id"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    TIME_CONSTRAINTS("time-constraints"),
    TYPE("type"),
    NAME_OF_ORGANISATION("name-of-organisation"),
    PASSPORT_NUMBER("passport-number");

    private String value;
    private DepositEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
