import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckingTest
{
    /**
     * useCheckingTooMuch: Uses Checking class, a sub class of Account.
     * @param None.
     * @return None.
     */
    @Test
    public void useCheckingTooMuch()
    {
        // Create checking object.
        Checking CheckingAccount = new Checking("Tester", "0000", new Money(5, 0), new Money(50, 0));
        
        // Withdraw too much.
        CheckingAccount.withdrawal(new Money(999999, 0));
        
        // Expected result.
        System.out.println("Withdrawing above overdraft protection: The amount of money we expect to have is $5.00.");
        
        // Actual result.
        System.out.println("Withdrawing above overdraft protection: The amount of money in balance: " + CheckingAccount.getBalance());
        
        // Assert true.
        assertTrue("Error in useCheckingTooMuch", CheckingAccount.getBalance().equals(new Money(5, 0)));
    }
    
    /**
     * useCheckingLessThanOverdraftPlusBalance: Uses Checking class and attempts to withdraw less than the amount
     * of overdraft plus the current balance.
     * @param None.
     * @return None.
     */
    @Test
    public void useCheckingLessThanOverdraftPlusBalance()
    {
        // Create checking object.
        Checking CheckingAccount = new Checking("Tester", "0000", new Money(5, 0), new Money(50, 0));
        
        // Withdraw too much.
        CheckingAccount.withdrawal(new Money(1, 0));
        
        // Expected result.
        System.out.println("Withdrawing above overdraft protection: The amount of money we expect to have is $4.00.");
        
        // Actual result.
        System.out.println("Withdrawing above overdraft protection: The amount of money in balance: " + CheckingAccount.getBalance());
        
        // Assert true.
        assertTrue("Error in useCheckingTooMuch", CheckingAccount.getBalance().equals(new Money(4, 0)));
    }
    
    /**
     * withdrawExactAmountOverdraftPlusBalance: withdraws the exact amount of overdraft plus balance from the current account.
     * @param None.
     * @return None.
     */
    @Test
    public void withdrawExactAmountOverdraftPlusBalance()
    {
        // Create checking object.
        Checking CheckingAccount = new Checking("Tester", "0000", new Money(5, 0), new Money(50, 0));
        
        // Withdraw too much.
        CheckingAccount.withdrawal(new Money(55, 0));
        
        // Expected result.
        System.out.println("Withdrawing above overdraft protection: The amount of money we expect to have is $-50.00");
        
        // Actual result.
        System.out.println("Withdrawing above overdraft protection: The amount of money in balance: " + CheckingAccount.getBalance());
        
        // Assert true.
        assertTrue("Error in useCheckingTooMuch", CheckingAccount.getBalance().equals(new Money(-50, 0)));
    }
    
}