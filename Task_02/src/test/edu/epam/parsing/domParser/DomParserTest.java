package test.edu.epam.parsing.domParser;

import edu.epam.parsing.entity.Deposit;
import edu.epam.parsing.entity.DepositType;
import edu.epam.parsing.entity.LegalPersonDeposit;
import edu.epam.parsing.exception.DepositException;
import edu.epam.parsing.parser.DepositBuilderFactory;
import edu.epam.parsing.parser.DepositDomBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.Set;

import static org.testng.Assert.*;

public class DomParserTest {
    private Set<Deposit> deposits;
    private final static String FILE_NAME = "resources/deposits.xml";
    @BeforeClass
    public void setUp() throws DepositException {
        DepositDomBuilder builder = (DepositDomBuilder) DepositBuilderFactory.createDepositBuilder("DOM");
        builder.buildSetDeposits(FILE_NAME);
        deposits = builder.getDeposits();
    }

    @AfterClass
    public void tearDown(){
        deposits = null;
    }

    @Test
    public void domParserTestTrue() throws DepositException {
        LegalPersonDeposit legalPersonDeposit = new LegalPersonDeposit();
        legalPersonDeposit.setName("Belarusbank");
        legalPersonDeposit.setCountry("Belarus");
        legalPersonDeposit.setDepositor("Lashuk");
        legalPersonDeposit.setAccountId("d1");
        legalPersonDeposit.setType(DepositType.ON_DEMAND);
        legalPersonDeposit.setAmountOnDeposit(5000);
        legalPersonDeposit.setProfitability(7);
        legalPersonDeposit.setTimeConstraints(YearMonth.parse("2019-05"));
        legalPersonDeposit.setNameOfOrganization("LashukGroups");
        boolean actual = deposits.contains(legalPersonDeposit);
        assertTrue(actual);
    }

    @Test
    public void domParserTestFalse() throws DepositException {
        LegalPersonDeposit legalPersonDeposit = new LegalPersonDeposit();
        legalPersonDeposit.setName("Belinvestbank");
        legalPersonDeposit.setCountry("Belarus");
        legalPersonDeposit.setDepositor("Rozum");
        legalPersonDeposit.setAccountId("d2");
        legalPersonDeposit.setType(DepositType.TERM);
        legalPersonDeposit.setAmountOnDeposit(5000);
        legalPersonDeposit.setProfitability(7);
        legalPersonDeposit.setTimeConstraints(YearMonth.parse("2019-05"));
        legalPersonDeposit.setNameOfOrganization("DenisovaInc");
        boolean actual = deposits.contains(legalPersonDeposit);
        assertFalse(actual);
    }

    @Test
    public void domParserLengthTest() {
        int actual = deposits.size();
        int expected = 16;
        assertEquals(actual, expected);
    }
}