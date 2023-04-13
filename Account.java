/**
 * A blueprint for Account objects.
 * 
 * Sabawoon Popal
 * 3-18-2021
 * 
 * QUESTION 2)
 */

public class Account implements Comparable
{
    // Declare attributes of Account.
    private String name, id;
    private Money balance;
    public Account(String theName, String theID, Money thebalance)
    {
        this.name = theName;
        this.id = theID;
        this.balance = thebalance;
    }
    
    // This allows us to extract the name attribute of the object.
    public String getName()
    {
        return this.name;
    }
    
    // This allows us to extract the id attribute of the object.
    public String getID()
    {
        return this.id;
    }
    
    // This allows us to extract the balance of the object.
    public Money getBalance()
    {
        return this.balance;
    }
    
    // This updates the balance based on the amount of money deposited.
    /**
     * Parameters: Class Money, the amount of money being deposited.
     * Preconditions: There is an object with the Money object to deposit money into.
     * Postcondition: The balance is updated by adding the incoming money.
     */
    public void deposit(Money incomingMoney)
    {
        // WE CAN ACCESS THE ADD METHOD FROM MONEY. USE THE ADD METHOD TO DEPOSIT AND UPDATE this.balance.
        this.balance = this.balance.add(incomingMoney); // balance = new Money(...);
    }
    
    /**
     * This updates the balance based on the amount of money withdrawed.
     * 
     * Parameters: Class Money, the amount of money being taken out.
     * Preconditions: There is an object with the Money object to deposit money into.
     * Postcondition: The balance is updated by subtracting the money withdrawed.
     */
    public void withdrawal(Money takingOutMoney)
    {
        // WE CAN ACCESS THE SUBTRACT METHOD FROM MONEY. USE THE SUBTRACT METHOD TO WITHDRAW AND UPDATE this.balance.
        this.balance = this.balance.subtract(takingOutMoney);
    }
    
    /**
     * This method transfers money from one Account object to another Account object.
     * 
     * Parameters: Class Account, the Account name that is receiving money. Class Money, the amount of money being sent.
     * Precondition: The above parameters are entered appropriately as well as there being two accounts to engage in a transfer.
     * Postcondition: The account receiving money will have their money deposited, whereas the other will be considered a withdraw.
     */
    public void transfer(Account receivingAccount, Money amountBeingReceived)
    {
        // THE ACCOUNT IN THE PARAMETER IS THE ACCOUNT RECEIVING MONEY.
        
        // Which account are we transferring from?  the Account object that invoked transfer => this
        // Which account are we transferring to?   receivingAccount
        
        
        // Call withdrawal on one Account object
        this.withdrawal(amountBeingReceived);
        
        // Call deposit on other Account object
        receivingAccount.deposit(amountBeingReceived);
    }
    
    /**
     * This returns the attributes of Account in string format.
     * 
     * Parameters: None
     * Precondition: There is a name, id, and money attribute entered down when creating the Account object.
     * Postcondition: The string format of the attributes will be returned.
     */
    public String toString()
    {
        String result = "Name: " + this.getName() + ". ID: " + this.getID() + ". " + this.balance;
        return result;
    }
    
    /**
     * This returns either true or false depending on whether two Account objects have the same exact data.
     * 
     * Parameters: Account of either individual
     * Precondition: Two accounts must be available to compare with the appropriate parameters when invoking.
     * Postcondition: Return true if same data, false if otherwise.
     */
    public boolean equals(Account other)
    {
        return(this.name.equals(other.name) && this.id.equals(other.id) && this.balance.equals(other.balance));
    }
    
    // compareTo: compare two Account objects.
    // Precondition: parameter o is an Object (of type Account)
    // Postcondition: return 0 if this.id is the same as o.id, -1 if this.id < o.id, 1 if this.id > o.id.
    // NORMALLY A PREDEFINED METHOD, BUT WE ARE OVERRIDING TO USE THIS INSTEAD OF THE PREDEFINED. WHICH IS WHY 
    // THIS ACCOUNT CLASS IMPLEMENTS COMPARABLE
    @Override 
    public int compareTo(Object o)
    {
        
        if (o instanceof Account) 
        {
            // return 0 if this.id is the same as o.id
            Account accountToCompare = (Account) o;
            if(this.id.equals(accountToCompare.id))
            {
                return 0;
            }
            else if(this.id.compareTo(accountToCompare.id) < 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        } 
        
        // o is not an Account ...
        return Integer.MAX_VALUE;
    }
    
    
}