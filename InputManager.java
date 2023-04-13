import java.util.Scanner;
/**
 * Manage input to be read from either keyboard or file.
 * 
 * @author Sabawoon Popal
 * @version 4-22-2021
 * 
 * Needed for Lab 20
 */
public class InputManager
{
    /**
     * Method: readOneAccountFrom
     * 
     * Precondition: inputSource is a Scanner object, already set up
     * to read from a text file or standard input source (keyboard).\
     * 
     * Postcondition: returns an Account with the data read for its attributes.
     *
     * Assumption: Account data will be in the format of: name,id,balance
     */
    public static Account readOneAccountFrom (Scanner inputSource)
    {
        // Read one line of account data into oneLine
        System.out.println ("Reading: name,id,balance");
        String oneLine = inputSource.nextLine();
        
        // Parse line of account data, separated by commas.
        Scanner lineTokenizer = new Scanner (oneLine);
        lineTokenizer.useDelimiter (",");
        
        // Get account data (i.e. name, accountNum and balance) from oneLine.
        // The textfile BANKINFO has information of three people with ID's, and 
        // and the dollars they have, and the cents they have. Make variables for
        // dollars and cents, and make it a money object.
        String name = lineTokenizer.next ();
        String accountNum = lineTokenizer.next();
        int dollars = lineTokenizer.nextInt ();
        int cents = lineTokenizer.nextInt();
        Money balance = new Money(dollars, cents);
        
        // Create and return an Account object with the data read for one account.
        Account oneAccount = new Account (name, accountNum, balance);
        System.out.println ("Account read: " + oneAccount);
        
        return oneAccount;
    } // end readOneAccountFrom
    
} // end InputManager
