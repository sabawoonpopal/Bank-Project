import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class BankTest.
 *
 * @author  SABAWOON POPAL
 * @version 4-6-2021
 * 
 * EDITED FOR LAB 15 QUESITON 2) testsortAccounts
 */
public class BankTest
{
    Bank _bankInfo;
    /**
     * Default constructor for test class BankTest
     */
    public BankTest()
    {
        // Nothing.
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        _bankInfo = new Bank("ADT");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        _bankInfo = null;
    }
    
    /**
     * testSearch: Tests to see if search method in bank account works.
     */
    @Test
    public void testSearch()
    {
        Account newAccounts = new Account("Testing", "1000", new Money(10, 0));
        Account anothernewAccounts = new Account("Second Testing", "2000", new Money(20, 0));
        
        _bankInfo.addAccount(newAccounts);
        _bankInfo.addAccount(anothernewAccounts);
        
        Account expectedResult = new Account("Second Testing", "2000", new Money(20, 0));
        Account actualResult = _bankInfo.search("2000");
        
        assertTrue("Error in testSearch", expectedResult.equals(actualResult));
    }
    
    /**
     * Test methods. This will test to see if addAccount method in Bank class works.
     */
    @Test
    public void testAddAccount()
    {
        // Actual account. Literally creating a new account object.
        Account actualNewAccount = new Account("Saba", "1175", new Money(1, 10)); 
        _bankInfo.addAccount(actualNewAccount);
        
        // Expected account. Using search method to find out if it's been added or not.
        Account expectedAccount = _bankInfo.search("1175");
        
        // Compare the actual and expected Accounts in Bank.
        // equals METHOD IS FROM ACCOUNT CLASS. WE DONT NEED TO MAKE ANOTHER EQUALS METHOD
        // IN THE BANK CLASS.
        assertTrue("Error in testAddAccount", expectedAccount.equals(actualNewAccount));
    }
    
    /**
     * This will test to see if deposit method in Bank class works.
     */
    @Test
    public void testDeposit()
    {
        // Since the data in our bank object is null, we must add another account
        // to it for this method.
        Account theExistingAccount = new Account("Saba", "1175", new Money(3, 40));
        _bankInfo.addAccount(theExistingAccount);
        
        // Depositing the amount indicated right below.
        _bankInfo.deposit("1175", new Money(3, 50));
        
        Account actualResult = _bankInfo.search("1175");
        
        // The actual balance of the following account after depositing.
        Money actualBalanceAfterDeposit = actualResult.getBalance();
        
        // The expected balance of the following account after depositing.
        Money expectedBalance = new Money(6, 90);
        
        // Assert the deposit comparison true.
        assertTrue("Error in testAddAccount", expectedBalance.equals(actualBalanceAfterDeposit));
       
        
    }
    
    /**
     * testWithdraw: Tests to see if withdraw works in bank class.
     */
    @Test
    public void testWithdraw()
    {
        // Bank object became null after previous test method, add a new account.
        Account accountExist = new Account("Saba", "1175", new Money(10, 50));
        _bankInfo.addAccount(accountExist);
        
        // Withdrawing the amount indicated below using the withdraw method.
        _bankInfo.withdraw("1175", new Money(7, 25));
        
        // Using the search to extract the account object, which will be used to find balance.
        Account accountOfFocus = _bankInfo.search("1175");
        
        // Actual balance of the withdraw.
        Money actualResult = accountOfFocus.getBalance();
        
        // Expected balance after withdrawing.
        Money expectedBalance = new Money(3, 25);
        
        // Assert the withdraw comparison true to test it.
        assertTrue("Error in testWithdraw", expectedBalance.equals(actualResult));
    }
    
    /**
     * testtoString: Tests to see if a string format of info in bank successfully displays.
     */
    @Test
    public void testtoString()
    {
        // Creating a new account object, and adding it to the bank object.
        Account theAccount = new Account("Saba", "1175", new Money(3, 50));
        _bankInfo.addAccount(theAccount);
        
        // Extracting the toString of the account object.
        String actualBankStringFormat = _bankInfo.toString();
        
        // Expected string format.
        System.out.println(actualBankStringFormat);
        // At this point
        
        // Expected variable.
        String expectedBankStringFormat = "ADT: 100 accounts stored. Name: Saba. ID: 1175. $3.50 ";
        
        // Assert True.
        assertTrue("Error in testtoString", expectedBankStringFormat.equals(actualBankStringFormat));
        
        
    }
    
    /**
     * testsortAccounts: Tests to see if the sortAccount method works in bank class.
     */
    @Test
    public void testsortAccounts()
    {
        // Create multiple account objects to test out the sorting method.
        Account oneToSort = new Account("Saba", "1175", new Money(5,50));
        Account shouldBeLast = new Account("I'm last", "9999", new Money(4, 45));
        Account shouldBeFirst = new Account("I'm first", "0001", new Money(10, 34));
        
        // Add these accounts into the bank object.
        _bankInfo.addAccount(oneToSort);
        _bankInfo.addAccount(shouldBeLast);
        _bankInfo.addAccount(shouldBeFirst);
        
        // Sort them using the sortAccounts method from the Bank class.
        _bankInfo.sort();
        
        // Using the print statement to analyze output.
        System.out.println(_bankInfo);
        
        String expectedResult = "ADT: 100 accounts stored. Name: I'm first. ID: 0001. $10.34 Name: Saba. ID: 1175. $5.50 Name: I'm last. ID: 9999. $4.45 ";
        
        String actualResult = _bankInfo.toString();
        
        // Assert true.
        assertTrue("Error in testsortAccounts", expectedResult.equals(actualResult));
    }
}
