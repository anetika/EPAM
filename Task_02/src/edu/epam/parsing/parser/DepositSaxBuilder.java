package edu.epam.parsing.parser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.exception.DepositException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class DepositSaxBuilder extends DepositBuilder{
    static Logger logger = LogManager.getLogger();
    private DepositHandler handler = new DepositHandler();
    private XMLReader reader;
    public DepositSaxBuilder() throws DepositException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e){
            logger.error("Problems with parsing");
            throw new DepositException("Problems with parsing");
        }
    }

    public Set<Deposit> getDeposits(){
        return deposits;
    }

    public void buildSetDeposits(String filename) throws DepositException{
        try {
            reader.parse(filename);
        } catch (SAXException e){
            logger.error("Problems with parsing");
            throw new DepositException("Problems with parsing");
        } catch (IOException e){
            logger.error("Problems with file");
            throw new DepositException("Problems with file");
        }

        deposits = handler.getDeposits();
        logger.info("SAX parser created a set of deposits");
    }
}
