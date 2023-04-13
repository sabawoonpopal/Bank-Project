import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Bank2: Another version of the Bank class.
 * Instead of using arrays, it uses array lists.
 *
 * @author Sabawoon Popal
 * @version 4-22-2021
 */
public class Bank2 implements BankInterface
{
    // Declare attributes of Bank 2. 
    // Difference: Bank 2 will use an array list
    // instead of array.
    private String bankName;
    private ArrayList<Account> accountsInArrayList;
    
    public Bank2(String theBankName)
    {
        this.bankName = theBankName;
        this.accountsInArrayList = new ArrayList<Account>();
        
    }
    
    /**
     * addAccount: Adds an account to the bank object, in the arraylist.
     * 
     * @param An Account object, this will be the account to add.
     * @return None.
     */
    public void addAccount(Account theNewAccount)
    {
        this.accountsInArrayList.add(theNewAccount);
    }
    
    /**
     * search: Searches for an account in the bank object.
     * 
     * @param String representing the ID number of the account.
     * @return An account if the parameter matches the ID Number.
     */
    public Account search(String ID_Number)
    {
        // Linear search:
        //for(int i = 0; i < this.accountsInArrayList.size(); i++)
        //{
        //    
        //    if(ID_Number.equals(this.accountsInArrayList.get(i).getID()))
        //    {
        //        return this.accountsInArrayList.get(i);
        //    }
        // }
        // // Invariant: ID_Number was not found.
        // return null;
        
        int indexNumberOfAccount = binarySearch(this.accountsInArrayList, 0, this.accountsInArrayList.size(), ID_Number);
        
        if(indexNumberOfAccount == -1)
        {
            return null;
        }

        Account accountFound = this.accountsInArrayList.get(indexNumberOfAccount);
     

        return accountFound;
    }
    
    /**
     * binarySearch: Helper method for search.
     *
     */
    public static int binarySearch(ArrayList<Account> anArray, int first, int last, String value) {

        // Searches the array items anArray[first] through
        // anArray[last] for value by using a binary search.
        // Precondition: 0 <= first, last <= SIZE-1, where
        // SIZE is the maximum size of the array, and
        // anArray[first] <= anArray[first+1] <= ... <= anArray[last].
        // Postcondition: If value is in the array, the method
        // returns the index of the array item that equals value;
        // otherwise the method returns -1.
    
        int index;

        if (first > last) {

            index = -1;      // value not in original array

        } 
        else {

            // Invariant: If value is in anArray, 
            // anArray[first] <= value <= anArray[last]

            int mid = (first + last)/2;

            if (value.equals(anArray.get(mid).getID())){

                index = mid;  // value found at anArray[mid]

            } 
            else if (value.compareTo(anArray.get(mid).getID()) < 0) {

                index = binarySearch(anArray, first, mid-1, value);   // point X

            } 
            else {

                index = binarySearch(anArray, mid+1, last, value);    // point Y

            }  // end if
        }  // end if

        return index;
    }  // end binarySearch
    
    /**
     * copyTextFile: Creates a new textfile showing the updated/changed information of the accounts after a transaction.
     * 
     * @param String representing the file name of the original text file.
     * @return None.
     */
    public void copyTextFile(String copyFileName)
    {
        PrintWriter ofStream = null; // declare an output file stream
        try {

            // Initialize the output file stream, based on the name of the output file, stored in copyFileName
            ofStream = new PrintWriter(new FileWriter(copyFileName)); 


            // copy lines one at a time from given file to new file
            for(int i = 0; i < this.accountsInArrayList.size(); i++) {
                ofStream.println(this.accountsInArrayList.get(i));  // Write a line to the output file.
            } // end while

            // close the file
            ofStream.close();
    
        } // end try
        catch (IOException e) {
            System.out.println("Error copying file");
        } // end catch
        finally {
            if (ofStream != null) {
                ofStream.close();
            } // end if
        } // end finally
    } // end copyTextFile
    
    /**
     * deposit: Deposits money into the account were working on.
     * 
     * @param String representing the ID number to deposit the money in, Money describing the amount of money to deposit into the account.
     * @return None.
     */
    public void deposit(String ID_Number, Money amountToDeposit)
    {
        // Obtain the account to deposit in using the search method which returns an account.
        Account accountToDepositIn = search(ID_Number);
        
        // Use the deposit method from account class to deposit the money.
        accountToDepositIn.deposit(amountToDeposit);
    }
    
     /**
     * withdraw: Withdraws money from the account were working on.
     * 
     * @param String representing the ID number to withdraw the money from, Money describing the amount of money to withdraw from the account.
     * @return None.
     */
    public void withdraw(String ID_Number, Money amountToWithdraw)
    {
        Account accountToWithdrawIn = search(ID_Number);
        // Obtain the account to withdraw from using the search 
        
        // Use the withdraw method from account class to withdraw the money.
        accountToWithdrawIn.withdrawal(amountToWithdraw);
    }
    
    /**
     * sort: Sorts the accounts in ID lowest-to-highest order.
     * 
     * @param None.
     * @return None.
     */
    public void sort()
    {
        Collections.sort(this.accountsInArrayList);
    }
    
    /**
     * toString: String format of the information/data stored in the bank object.
     * 
     * @param None.
     * @return String representing the information (account data) in bank object.
     */
    public String toString()
    {
        String result; 
        result = this.bankName + ": " + this.accountsInArrayList.size() + " accounts stored. ";
        
        for(int i = 0; i < this.accountsInArrayList.size(); i++)
        {
            result += this.accountsInArrayList.get(i).toString() + " ";
        }
        
        return result;
    }
}
