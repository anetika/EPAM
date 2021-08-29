package test.edu.epam.parsing.saxParser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.entity.DepositType;
import edu.epam.parsing.entity.LegalPersonDeposit;
import edu.epam.parsing.exception.DepositException;
import edu.epam.parsing.parser.DepositBuilderFactory;
import edu.epam.parsing.parser.DepositSaxBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.Set;

import static org.testng.Assert.*;

public class SaxParserTest {
    private final static String FILE_NAME = "resources/deposits.xml";
    private Set<Deposit> deposits;
    @BeforeClass
    public void setUp() throws DepositException {
        DepositSaxBuilder builder = (DepositSaxBuilder) DepositBuilderFactory.createDepositBuilder("SAX");
        builder.buildSetDeposits(FILE_NAME);
        deposits = builder.getDeposits();
    }
    @AfterClass
    public void tearDown(){
        deposits = null;
    }
    @Test
    public void DepositSaxParserLengthTest() throws DepositException {
        int expected = 16;
        int actual = deposits.size();
        assertEquals(actual, expected);
    }
    @Test
    public void DepositSaxParserTestTrue(){
        LegalPersonDeposit deposit = new LegalPersonDeposit();
        deposit.setName("Tinkoff");
        deposit.setCountry("Russia");
        deposit.setDepositor("Kovaleva");
        deposit.setAccountId("d6");
        deposit.setAmountOnDeposit(5000);
        deposit.setProfitability(7);
        deposit.setNameOfOrganization("KovalevaProd");
        deposit.setTimeConstraints(YearMonth.parse("2019-05"));
        deposit.setType(DepositType.TERM);
        boolean actual = deposits.contains(deposit);
        assertTrue(actual);
    }
    @Test
    public void DepositSaxParserTestFalse(){
        LegalPersonDeposit deposit = new LegalPersonDeposit();
        deposit.setName("Belinvestbank");
        deposit.setCountry("Belarus");
        deposit.setDepositor("Gelmanov");
        deposit.setAccountId("d10");
        deposit.setAmountOnDeposit(5000);
        deposit.setProfitability(7);
        deposit.setTimeConstraints(YearMonth.parse("2019-05"));
        deposit.setNameOfOrganization("dadfdafad");
        deposit.setType(DepositType.METALLIC);
        boolean actual = deposits.contains(deposit);
        assertFalse(actual);
    }
}
