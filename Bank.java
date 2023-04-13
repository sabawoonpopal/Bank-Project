/**
 * A blueprint for Bank objects, and creating an array of bank accounts.
 * 
 * Sabawoon Popal
 * 3-23-2021
 * 
 * LAB 12 QUESTION 2)
 */

// LAB 15 QUESTION 2) USE "implements BankInterface"
public class Bank implements BankInterface{
    // Declare attributes of Bank class.
    private String nameOfBank;
    private Account[] accounts;
    private int numOfAccounts;
    private int slot;
    /**
     * Constructor: Initializes all attributes (i.e. nameOfBank, accounts).
     * 
     * @param numOfAccounts, the number of Account objects there will be in the array of objects.
     */
    public Bank(String theNameOfBank)
    {
        // Initialize the attributes.
        this.numOfAccounts = 100;
        this.nameOfBank = theNameOfBank;
        this.accounts = new Account[numOfAccounts];
        this.slot = 0;
    }
    
    /**
     * addAccount: Adds an account to the bank object.
     * 
     * 
     */
    public void addAccount(Account theNewAccount)
    {
       // int slotNumber = 0;
        // Create an account object, store it in this.accounts.
        if (this.slot < this.numOfAccounts)
        {
            this.accounts[this.slot] = theNewAccount;
        }
        
        // Since we used up a slot, increment slotNumber.
        this.slot = this.slot + 1;
  
        
    }
    
    /**
     * search: Searches for an account in the bank object.
     * 
     * @param String representing the ID number of the account.
     * @return An account if the parameter matches the ID Number.
     */
    public Account search(String ID_Number)
    {
        int index = 0;
        for(int i = 0; i < this.slot; i++)
        {
            
            if(ID_Number.equals( this.accounts[i].getID()))
            {
                index = i;
            }
           
        }
        
        return this.accounts[index];
    } 
    
    // Not implemented yet. (Needed a copyTextFile for bank2. Leaving this blank.
    public void copyTextFile(String copyFileName)
    {
    }
    
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
     * sort: Sorts the accounts.
     * 
     * @param None.
     * @return None.
     */
    public void sort()
    {
        // Syntax rule for calling a static method (or class method): 
        // ClassName.methodName(parameters)
        System.out.println(this.slot);
        SortsClass.selectionSort(this.accounts, this.slot);
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
        result = this.nameOfBank + ": " + this.numOfAccounts + " accounts stored. ";
        
        for(int i = 0; i < this.slot; i++)
        {
            result += this.accounts[i].toString() + " ";
        }
        
        return result;
    }
    
}