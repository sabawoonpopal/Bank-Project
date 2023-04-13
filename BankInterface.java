import java.util.ArrayList;
/**
 * Write a description of interface BankInterface here.
 *
 * @author SABAWOON POPAL
 * @version 4-6-2021
 * LAB 15 QUESTION 1)
 */
public interface BankInterface
{
    /**
     * addAccount: Adds a new account object in the bank object.
     */
    public void addAccount(Account theNewAccount);
    
    /**
     * search: Searches for an account based on the ID number given. (FOR BANK).
     * 
     * @return an Account object that has the inputted ID number
     */
    public Account search(String ID_Number);
    
    /**
     * deposit: Deposits money into an Account object in the Bank object.
     */
    public void deposit(String ID_Number, Money amountToDeposit);
    
    /**
     * withdraw: Withdraws money from an Account object in the Bank object.
     */
    public void withdraw(String ID_Number, Money amountToWithdraw);
    
    /**
     * copyTextFile: Copies the old file data into a new one (output file).
     */
    public void copyTextFile(String copyFileName);
    
    /**
     * sort: sorts the accounts in either bank class.
     */
    public void sort();
    
    /**
     * toString:
     * 
     * @return a String representation of a Bank that includes all attributes.
     */
    public String toString();
}
