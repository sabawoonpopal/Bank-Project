import java.util.Scanner;
import java.io.File;
import java.io.IOException;

// Driver class for Bank project
/**
 * ATM: Driver class for BankInterface, where BankInterface allows
 * us to choose whether we want to create an object of Bank or Bank2.
 * 
 * @author Sabawoon Popal
 * @version 4-22-2021
 * 
 * Lab 20 Question 1) & 2)
 */
public class ATM
{
   /**
    * main: Driver method in the ATM class. Implements all possible classes involved with this bank project.
    */
    public static void main (String[] args)
   {
       try 
       {

          // Read data from a file into a Bank.
          // Each line of the file has info for one account. 
          // MAKE SURE THE TEXTFILE (notepad file) IS IN THE SAME LOCATION AS CSC175Lab, 
          // OTHERWISE IT WONT WORK. I DID THIS ALREADY. THE TEXTFILE IS CALLED BANKINFO.
          BankInterface allBankInfo = readFromFile("BankInformationInputFile.txt");
          
          
          // SORT ACCOUNTS IN INPUT FILE. REQUIRED FOR BINARY SEARCH.
          allBankInfo.sort();
          
          
          // Call readUserID from readUserID method in this class.
          // YOU MUST CREATE IOHandlerInterface OBJECT TO CALL THE METHOD
          // USING A IOHandlerInterface OBJECT AS A PARAMETER.
          // Lab 20 Question 2): This asks for the user ID on a separate panel window.
          IOHandlerInterface ioh = new IOHandlerDialog();
          String ID = readUserID(ioh);
          
    
    
          // OR, instead of creating and calling what's above, we can use the Standard instead of the Dialog by 
          // creating the object and calling it with that method. Either the above, or the code below must be commented,
          // both shouldn't be uncommented at the same time.
          // Lab 20 Question 2): Update; IT WORKS. It shows the prompt on the terminal window instead of a
          // separate panel.
          // Right now: I WILL COMMENT THIS. AND UNCOMMENT THE OTHER.
    
          //IOHandlerInterface ioh2 = new IOHandlerStandard();
          //String ID2 = readUserID(ioh2);
    
          boolean accountExistsGivenID = isValid(allBankInfo, ID);
          
          
          if(accountExistsGivenID == true)
          {   
              ioh.put("There exists an account with the ID inputted.");
              Account loggedIn = allBankInfo.search(ID);
              String inputChoice = accountOptions(ioh);
              // if(inputChoice.equals("Check") ){
              // System.out.println("Your Balance");
               // }
               // else if input == deposit 
                // deposit money
               // else if input == withdraw 
                // withdraw money
               // else if input == quit 
                // break; 
               // else 
                // inputChoice = ioh.get("Error, out of range option. Please try again.");
               
              do
              {
                  //System.out.println(inputChoice);
                  if(inputChoice.equals("Check"))
                  {
                      ioh.put("Your balance is: " + loggedIn.getBalance());
                  }
                  else if(inputChoice.equals("Deposit"))
                  {
                      String depositDollars = howMuchDollars(ioh, inputChoice);
                      String depositCents = howMuchCents(ioh, inputChoice);
                      try
                      {
                            int inDollars = Integer.parseInt(depositDollars);
                            int inCents = Integer.parseInt(depositCents);
                            Money amountToDeposit = new Money(inDollars, inCents);
                            loggedIn.deposit(amountToDeposit);
                            ioh.put("Your new balance is: " + loggedIn.getBalance());
                      }
                      catch (NumberFormatException nfe)
                      {
                            ioh.put("Error. Illegal character inputted.");
                      }
                    }
                  else if(inputChoice.equals("Withdraw"))
                  {
                      String withdrawDollars = howMuchDollars(ioh, inputChoice);
                      String withdrawCents = howMuchCents(ioh, inputChoice);
                      try
                      {
                            int inDollars = Integer.parseInt(withdrawDollars);
                            int inCents = Integer.parseInt(withdrawCents);
                            Money amountToWithdraw = new Money(inDollars, inCents);
                            loggedIn.withdrawal(amountToWithdraw);
                            ioh.put("Your new balance is: " + loggedIn.getBalance());
                      }
                      catch (NumberFormatException nfe)
                      {
                          ioh.put("Error. Illegal character inputted.");
                      }
                      catch (Exception e)
                      {
                            ioh.put("Error. Invalid entry.");
                      }
                  }
                  else if (inputChoice.equals("Quit"))
                  {
                      break;
                  }
              }while ( !(inputChoice.equals("Check") || inputChoice.equals("Deposit") || inputChoice.equals("Withdraw"))); // (! (valid))
          }
          else
          {
              ioh.put("An account with such ID does not exist.");
          }

          // Print all the data stored in the bank.
          System.out.println(allBankInfo);
          
          // User is done, copy account information (all) into an output file.
          allBankInfo.copyTextFile("BANKINFO.txt");

       } // end try  
       catch (IOException ioe) {
         System.out.println("IOException in main: " + ioe.getMessage()); 
         ioe.printStackTrace();
       } // end catch
       catch (Exception e) {
         System.out.println("Exception in main: " + e.getMessage());
         e.printStackTrace();
       } // end catch
   } // end main
   
   /**
    * accountOptions: Uses a dialog to obtain the users option of checking balance, depositing, or withdrawing from their account.
    */
   public static String accountOptions(IOHandlerInterface ioh)
   {
       return ioh.get("Enter 'Check' to check balance, 'Deposit' to deposit, 'Withdraw' to withdraw: ");
    }
   
    /**
     * howMuchDeposit: Uses a dialog to obtain the amount of money the user wants to deposit/withdraw in dollars.
     */
    public static String howMuchDollars(IOHandlerInterface ioh, String inputChoice)
    {
        return ioh.get("Enter the dollars amount in numbers of the amount you want to " + inputChoice + ":");
    }
    
    /**
     * howMuchCents: Uses a dialog to obtain the amount of money the user wants to deposit/withdraw in dollars.
     */
    public static String howMuchCents(IOHandlerInterface ioh, String inputChoice)
    {
        return ioh.get("Enter the cents amount in numbers of the amount you want to " + inputChoice + ":");
    }
    
   /**
    * readUserID: Uses a dialog to obtain the users ID. A small window will pop up and asks for an ID.           
    */
   public static String readUserID(IOHandlerInterface ioh)
   {
        return ioh.get("Please enter your ID:");
   }
   
   /**
    * readFromFile: Reads the information of accounts from the textfile.
    * 
    * @param a String which is the textfile. (NOT IMPLEMENTED HERE, JUST CONSTRUCTED.)
    * @return a Bank with the accounts. using the information from the textfile.
    * 
    */
   public static BankInterface readFromFile (String fileName) throws IOException
   {
     // Create a bank.
     BankInterface myBank = new Bank2("The Demo Bank");

         // Open a file for reading.
         Scanner inputSource = new Scanner (new File(fileName));
       
         // while there are more tokens to read from the input source...
     while (inputSource.hasNext()) 
     {
        // Read one line of input from the file into an Account object.
        Account acct = InputManager.readOneAccountFrom(inputSource);
        
        // Store the account info in the bank.
        myBank.addAccount(acct);

     } // end while


         return myBank;

   } // end readFromFile
    
   /**
    * isValid: Reads an account ID and finds to see if there is an account with that ID.
    * 
    * @param bank parameter using BankInterface, and a String representing the ID number.
    * @return true if ID is in bank.
    */
   public static boolean isValid(BankInterface bank, String ID)
   {
       Account accountToLookFor = bank.search(ID);
       
       if(accountToLookFor == null)
       {
           return false;
       }
       
       return true;
   }
} // end ATM.