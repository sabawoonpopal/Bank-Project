public class Checking extends Account
{
    // Set up attribute.
    private Money overdraftMaximum;
    
    /**
     * Constructor: Initialize overdraftMaximum
     */
    public Checking(String name, String theID, Money thebalance, Money theOverdraftMaximum)
    {
        super(name, theID, thebalance);
        this.overdraftMaximum = theOverdraftMaximum;
        
    }
    
    /**
     * withdrawal: Withdraws money from Money thebalance.
     * @param Money takingOutMoney, the amount that is being withdrawed.
     */
    @Override
    public void withdrawal(Money takingOutMoney)
    {
        // Adding the balance with the overdraftMaximum (overdraft protection).
        Money sumOfBalanceAndOverdraft = this.overdraftMaximum.add(super.getBalance());
        if(sumOfBalanceAndOverdraft.compareTo(takingOutMoney) >= 0)
        {
            super.withdrawal(takingOutMoney);
        }
        // Since were being asked to not withdrawal if the sum of the overdraft protection and balance is less than
        // the amount we want to withdraw.
    }
    
}