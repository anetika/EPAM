package edu.epam.parsing.parser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.entity.DepositType;
import edu.epam.parsing.entity.LegalPersonDeposit;
import edu.epam.parsing.entity.PhysicalPersonDeposit;
import edu.epam.parsing.exception.DepositException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;

public class DepositDomBuilder extends DepositBuilder{
    static Logger logger = LogManager.getLogger();
    private DocumentBuilder builder;

    public DepositDomBuilder() throws DepositException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new DepositException("Something is wrong with parser");
        }
    }
    @Override
    public void buildSetDeposits(String filename) throws DepositException {
        Document document = null;
        try {
            document = builder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList legalPersonDepositList = root.getElementsByTagName("legal-person-deposit");
            for (int i = 0; i < legalPersonDepositList.getLength(); i++) {
                Element depositElement = (Element) legalPersonDepositList.item(i);
                LegalPersonDeposit deposit = buildLegalPersonDeposit(depositElement);
                deposits.add(deposit);
            }
            NodeList physicalPersonDepositList = root.getElementsByTagName("physical-person-deposit");
            for (int i = 0; i < physicalPersonDepositList.getLength(); i++) {
                Element depositElement = (Element) physicalPersonDepositList.item(i);
                PhysicalPersonDeposit deposit = buildPhysicalPersonDeposit(depositElement);
                deposits.add(deposit);
            }
        } catch (SAXException e) {
            logger.error("Problems with parser");
            throw new DepositException ("Problems with parser");
        } catch (IOException e) {
            logger.error("Something is wrong with file");
            throw new DepositException("Something is wrong with file");
        }
    }
    private void buildDeposit(Deposit deposit, Element depositElement){
        deposit.setName(getElementTextContent(depositElement, "name"));
        deposit.setCountry(getElementTextContent(depositElement, "country"));
        deposit.setDepositor(getElementTextContent(depositElement, "depositor"));
        deposit.setType(DepositType.valueOf(getElementTextContent(depositElement, "type").toUpperCase().replace('-', '_')));
        deposit.setAccountId(depositElement.getAttribute("account-id"));
        if (depositElement.hasAttribute("amount-on-deposit")){
            deposit.setAmountOnDeposit(Integer.parseInt(depositElement.getAttribute("amount-on-deposit")));
        } else{
            deposit.setAmountOnDeposit(5000);
        }
        if (depositElement.hasAttribute("profitability")){
            deposit.setProfitability(Integer.parseInt(depositElement.getAttribute("profitability")));
        } else{
            deposit.setProfitability(7);
        }
        deposit.setTimeConstraints(YearMonth.parse(depositElement.getAttribute("time-constraints")));
    }

    private LegalPersonDeposit buildLegalPersonDeposit(Element depositElement){
        LegalPersonDeposit deposit = new LegalPersonDeposit();
        buildDeposit(deposit, depositElement);
        deposit.setNameOfOrganization(getElementTextContent(depositElement, "name-of-organisation"));
        return deposit;
    }

    private PhysicalPersonDeposit buildPhysicalPersonDeposit(Element depositElement){
        PhysicalPersonDeposit deposit = new PhysicalPersonDeposit();
        buildDeposit(deposit, depositElement);
        deposit.setPassportNumber(Integer.parseInt(getElementTextContent(depositElement, "passport-number")));
        return deposit;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
