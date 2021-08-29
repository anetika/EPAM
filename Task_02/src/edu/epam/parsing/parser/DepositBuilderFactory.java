package edu.epam.parsing.parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.epam.parsing.exception.DepositException;
public class DepositBuilderFactory {
    static Logger logger = LogManager.getLogger();
    private enum TypeParser{
        SAX, STAX, DOM;
    }

    private DepositBuilderFactory(){

    }
    public static DepositBuilder createDepositBuilder(String typeParser) throws DepositException{
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        switch (type){
            case DOM -> {
                logger.info("DOM builder was created");
                return new DepositDomBuilder();
            }
            case SAX -> {
                logger.info("SAX builder was created");
                return new DepositSaxBuilder();
            }
            case STAX -> {
                logger.info("STAX builder was created");
                return new DepositStaxBuilder();
            }
            default -> {throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());}
        }
    }
}
