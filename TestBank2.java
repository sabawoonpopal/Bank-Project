

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestBank2.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestBank2
{
    Bank2 _bankDemo;
    /**
     * Default constructor for test class TestBank2
     */
    public TestBank2()
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
        _bankDemo = new Bank2("Fake Bank");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        _bankDemo = null;
    }
    
    /**
     * This will test to see if search method in Bank2 class works.
     */
    @Test
    public void testSearch()
    {
        Account newAccounts = new Account("Testing", "1000", new Money(10, 0));
        Account anothernewAccounts = new Account("Second Testing", "2000", new Money(20, 0));
        
        _bankDemo.addAccount(newAccounts);
        _bankDemo.addAccount(anothernewAccounts);
        
        Account expectedResult = new Account("Second Testing", "2000", new Money(20, 0));
        Account actualResult = _bankDemo.search("2000");
        
        assertTrue("Error in testSearch", expectedResult.equals(actualResult));
    }
    
    /**
     * Test methods. This will test to see if addAccount method in Bank2 class works.
     */
    @Test
    public void testAddAccount()
    {
        // Actual account. Literally creating a new account object.
        Account actualNewAccount = new Account("Saba", "1175", new Money(1, 10)); 
        _bankDemo.addAccount(actualNewAccount);
        
        // Expected account. Using search method to find out if it's been added or not.
        Account expectedAccount = _bankDemo.search("1175");
        
        // Compare the actual and expected Accounts in Bank.
        // equals METHOD IS FROM ACCOUNT CLASS. WE DONT NEED TO MAKE ANOTHER EQUALS METHOD
        // IN THE BANK CLASS.
        assertTrue("Error in testAddAccount", expectedAccount.equals(actualNewAccount));
    }
    
    /**
     * This will test to see if deposit method in Bank2 class works.
     */
    @Test
    public void testDeposit()
    {
        // Since the data in our bank object is null, we must add another account
        // to it for this method.
        Account theExistingAccount = new Account("Saba", "1175", new Money(3, 40));
        _bankDemo.addAccount(theExistingAccount);
        
        // Depositing the amount indicated right below.
        _bankDemo.deposit("1175", new Money(3, 50));
        
        Account actualResult = _bankDemo.search("1175");
        
        // The actual balance of the following account after depositing.
        Money actualBalanceAfterDeposit = actualResult.getBalance();
        
        // The expected balance of the following account after depositing.
        Money expectedBalance = new Money(6, 90);
        
        // Assert the deposit comparison true.
        assertTrue("Error in testAddAccount", expectedBalance.equals(actualBalanceAfterDeposit));
       
        
    }
    
    /**
     * This will test to see if withdraw method in Bank2 class works.
     */
    @Test
    public void testWithdraw()
    {
        // Bank object became null after previous test method, add a new account.
        Account accountExist = new Account("Saba", "1175", new Money(10, 50));
        _bankDemo.addAccount(accountExist);
        
        // Withdrawing the amount indicated below using the withdraw method.
        _bankDemo.withdraw("1175", new Money(7, 25));
        
        // Using the search to extract the account object, which will be used to find balance.
        Account accountOfFocus = _bankDemo.search("1175");
        
        // Actual balance of the withdraw.
        Money actualResult = accountOfFocus.getBalance();
        
        // Expected balance after withdrawing.
        Money expectedBalance = new Money(3, 25);
        
        // Assert the withdraw comparison true to test it.
        assertTrue("Error in testWithdraw", expectedBalance.equals(actualResult));
    }
    
    /**
     * This will test to see if toString method in Bank2 class works.
     */
    @Test
    public void testtoString()
    {
        // Creating a new account object, and adding it to the bank object.
        Account theAccount = new Account("Saba", "1175", new Money(3, 50));
        _bankDemo.addAccount(theAccount);
        
        // Extracting the toString of the account object.
        String actualBankStringFormat = _bankDemo.toString();
        
        // Expected string format.
        System.out.println(actualBankStringFormat);
        // At this point
        
        // Expected variable.
        String expectedBankStringFormat = "Fake Bank: 1 accounts stored. Name: Saba. ID: 1175. $3.50 ";
        
        // Assert True.
        assertTrue("Error in testtoString", expectedBankStringFormat.equals(actualBankStringFormat));
        
        
    }
    
    /**
     * This will test to see if sort method in Bank2 class works.
     */
    @Test
    public void testsortAccounts()
    {
        // Create multiple account objects to test out the sorting method.
        Account oneToSort = new Account("Saba", "1175", new Money(5,50));
        Account shouldBeLast = new Account("I'm last", "9999", new Money(4, 45));
        Account shouldBeFirst = new Account("I'm first", "0001", new Money(10, 34));
        
        // Add these accounts into the bank object.
        _bankDemo.addAccount(oneToSort);
        _bankDemo.addAccount(shouldBeLast);
        _bankDemo.addAccount(shouldBeFirst);
        
        // Sort them using the sortAccounts method from the Bank class.
        _bankDemo.sort();
        
        // Using the print statement to analyze output.
        System.out.println(_bankDemo);
        
        String expectedResult = "Fake Bank: 3 accounts stored. Name: I'm first. ID: 0001. $10.34 Name: Saba. ID: 1175. $5.50 Name: I'm last. ID: 9999. $4.45 ";
        
        String actualResult = _bankDemo.toString();
        
        // Assert true.
        assertTrue("Error in testsortAccounts", expectedResult.equals(actualResult));
    }
}
