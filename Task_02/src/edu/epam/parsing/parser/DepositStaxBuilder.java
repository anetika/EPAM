package edu.epam.parsing.parser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.entity.DepositType;
import edu.epam.parsing.entity.LegalPersonDeposit;
import edu.epam.parsing.entity.PhysicalPersonDeposit;
import edu.epam.parsing.exception.DepositException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;

public class DepositStaxBuilder extends DepositBuilder{
    static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;
    public DepositStaxBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetDeposits(String filename) throws DepositException {
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(filename))){
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase().replace('-', '_')) == DepositEnum.LEGAL_PERSON_DEPOSIT){
                        Deposit deposit = buildLegalPersonDeposit(reader);
                        deposits.add(deposit);
                    } else if (DepositEnum.valueOf(name.toUpperCase().replace('-', '_')) == DepositEnum.PHYSICAL_PERSON_DEPOSIT){
                        Deposit deposit = buildPhysicalPersonDeposit(reader);
                        deposits.add(deposit);
                    }
                }
            }
            logger.info("STAX parser created a set of deposits");
        } catch (FileNotFoundException | XMLStreamException e) {
            logger.error("Impossible to close the file");
            throw new DepositException("Impossible to close the file" + filename);
        } catch (IOException e) {
            logger.error("Impossible to close the file");
            throw new DepositException("Impossible to close the file" + filename);
        }
    }

    private LegalPersonDeposit buildLegalPersonDeposit(XMLStreamReader reader) throws XMLStreamException, DepositException{
        LegalPersonDeposit deposit = new LegalPersonDeposit();
        buildDeposit(deposit, reader);
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT){
                name = reader.getLocalName();
                if (DepositEnum.valueOf(name.toUpperCase().replace('-', '_')) == DepositEnum.NAME_OF_ORGANISATION){
                    deposit.setNameOfOrganization(getXMLText(reader));
                }
            } else if (type == XMLStreamConstants.END_ELEMENT){
                name = reader.getLocalName();
                if (DepositEnum.valueOf(name.toUpperCase().replace('-', '_')) == DepositEnum.LEGAL_PERSON_DEPOSIT){
                    return deposit;
                }
            }
        }
        throw new DepositException("Unknown element in tag");
    }

    private PhysicalPersonDeposit buildPhysicalPersonDeposit(XMLStreamReader reader) throws XMLStreamException, DepositException{
        PhysicalPersonDeposit deposit = new PhysicalPersonDeposit();
        buildDeposit(deposit, reader);
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT){
                name = reader.getLocalName();
                if (DepositEnum.valueOf(name.toUpperCase().replace('-', '_')) == DepositEnum.PASSPORT_NUMBER){
                    deposit.setPassportNumber(Integer.parseInt(getXMLText(reader).toUpperCase().replace('-', '_')));
                }
            } else if (type == XMLStreamConstants.END_ELEMENT){
                name = reader.getLocalName();
                if (DepositEnum.valueOf(name.toUpperCase().replace('-', '_')) == DepositEnum.PHYSICAL_PERSON_DEPOSIT){
                    return deposit;
                }
            }
        }
        throw new DepositException("Unknown element in tag");
    }

    private void buildDeposit(Deposit deposit, XMLStreamReader reader) throws XMLStreamException {
        deposit.setAccountId(reader.getAttributeValue(null, DepositEnum.ACCOUNT_ID.getValue()));
        if (reader.getAttributeValue(null, DepositEnum.AMOUNT_ON_DEPOSIT.getValue()) != null) {
            deposit.setAmountOnDeposit(Integer.parseInt(reader.getAttributeValue(null, DepositEnum.AMOUNT_ON_DEPOSIT.getValue())));
        } else {
            deposit.setAmountOnDeposit(5000);
        }
        if (reader.getAttributeValue( null, DepositEnum.PROFITABILITY.getValue()) != null) {
            deposit.setProfitability(Integer.parseInt(reader.getAttributeValue(null, DepositEnum.PROFITABILITY.getValue())));
        } else {
            deposit.setProfitability(7);
        }
        deposit.setTimeConstraints(YearMonth.parse(reader.getAttributeValue( null, DepositEnum.TIME_CONSTRAINTS.getValue())));

        String name;
        while (reader.hasNext()){
            int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    boolean flag = false;
                    switch (DepositEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
                        case NAME -> deposit.setName(getXMLText(reader));
                        case COUNTRY -> deposit.setCountry(getXMLText(reader));
                        case DEPOSITOR -> deposit.setDepositor(getXMLText(reader));
                        case TYPE -> {
                            deposit.setType(DepositType.valueOf(getXMLText(reader).toUpperCase().replace('-', '_')));
                            flag = true;
                        }
                    }
                    if (flag){
                        break;
                }
            }
        }
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
