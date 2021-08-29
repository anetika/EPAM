package edu.epam.parsing.parser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.entity.DepositType;
import edu.epam.parsing.entity.LegalPersonDeposit;
import edu.epam.parsing.entity.PhysicalPersonDeposit;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DepositHandler extends DefaultHandler {
    private Set<Deposit> deposits;
    private Deposit current;
    private DepositEnum currentEnum;
    private EnumSet<DepositEnum> withText;

    public DepositHandler() {
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositEnum.NAME, DepositEnum.PASSPORT_NUMBER);
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void startElement(String uri, String localName, String qname, Attributes attrs){
        if (DepositEnum.LEGAL_PERSON_DEPOSIT.getValue().equals(localName)){
            current = new LegalPersonDeposit();
            setAttributes(attrs, current);
        } else if (DepositEnum.PHYSICAL_PERSON_DEPOSIT.getValue().equals(localName)){
            current = new PhysicalPersonDeposit();
            setAttributes(attrs, current);
        } else {
            DepositEnum temp = DepositEnum.valueOf(localName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if (DepositEnum.LEGAL_PERSON_DEPOSIT.getValue().equals(localName) || DepositEnum.PHYSICAL_PERSON_DEPOSIT.getValue().equals(localName)){
            deposits.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null){
            switch (currentEnum){
                case NAME -> current.setName(s);
                case COUNTRY -> current.setCountry(s);
                case DEPOSITOR -> current.setDepositor(s);
                case TYPE -> current.setType(DepositType.valueOf(s.toUpperCase().replace('-', '_')));
                case NAME_OF_ORGANISATION -> {
                    LegalPersonDeposit deposit = (LegalPersonDeposit) current;
                    deposit.setNameOfOrganization(s);
                }
                case PASSPORT_NUMBER -> {
                    PhysicalPersonDeposit deposit = (PhysicalPersonDeposit) current;
                    deposit.setPassportNumber(Integer.parseInt(s));
                }
                default -> {
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
                }
            }
            currentEnum = null;
        }
    }
    private void setAttributes(Attributes attributes, Deposit current) {
        current.setAccountId(attributes.getValue("account-id"));
        current.setTimeConstraints(YearMonth.parse(attributes.getValue("time-constraints")));
        if (attributes.getValue("amount-on-deposit") != null){
            current.setAmountOnDeposit(Integer.parseInt(attributes.getValue("amount-on-deposit")));
        } else {
            current.setAmountOnDeposit(5000);
        }
        if (attributes.getValue("profitability") != null) {
            current.setProfitability(Integer.parseInt(attributes.getValue("profitability")));
        } else {
            current.setProfitability(7);
        }
    }
}
