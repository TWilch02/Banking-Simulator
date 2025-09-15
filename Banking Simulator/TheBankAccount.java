/* Name: Terence Wilchcombe
 Course: CNT 4714 Spring 2024
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 11, 2024
*/

package project2CNT;



import java.util.Random;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class TheBankAccount implements TheBank{
	// define the lock to manually control access to bank account
	private Lock accessLock = new ReentrantLock();

	//define variable conditions for lock
	private Condition canAccess = accessLock.newCondition();
	
	// define the variables and constants for bank account
	public static int accountBalance = 0;
	public static int internalAuditCount = 0;
	public static int treasuryDeptCount = 0;
	public static int flagCount = 0;
	public static int flagged = 0;
	public static int isDeposit = 0;
	public static int transactionNumber = 0;
	public int depositAmount = 0;
	public int withdrawAmount = 0;
	Object Depositor = new Object();
	Object Withdrawl = new Object();
	Random rand = new Random();
	public int get() {
		return accountBalance;
	}
	public void set(int x) {
		this.accountBalance = x;
	}
	
	
	
	public void deposit() {
		
		//lock account
		if (accessLock.tryLock()) {

		depositAmount = rand.nextInt(500) + 1;
			isDeposit = 1;
			try {
				//make deposit
				accountBalance += depositAmount;
				transactionNumber++;
			
				System.out.print("has deposited $" + depositAmount + "    Current account balance: " + accountBalance +"   Transaction number: " +transactionNumber + "\n" );


				internalAuditCount++;
				treasuryDeptCount++;
				
				if (depositAmount > 355) {
					flagged_transaction();
					
				}
				
			}
			catch (Exception e) {
				System.out.println(" Error thrown depositing ");
				
			}
			finally {
				accessLock.unlock();
			}
			isDeposit = 0;
		}
		
	}


	public void withdraw() {
		
		//lock
		if (accessLock.tryLock()) {
	
		withdrawAmount = rand.nextInt(100)+1;
		int accountCheck = get();
		
			try {
				//check balance
				if(withdrawAmount > accountCheck) {
					System.out.println("****** WITHDRAW BLOCKED - INSUFFICINT FUNDS. Requested amount: " + withdrawAmount);
					System.out.println("Current account balance is: " + accountBalance);
					
				}
				else {
					
					
		
					accountCheck -= withdrawAmount;
					transactionNumber++;
					
					this.set(accountCheck);
					System.out.print("has withdrawn $" + withdrawAmount + "    Current account balance: " + accountBalance + "   Transaction number: " +transactionNumber + "\n" );
					internalAuditCount++;
					treasuryDeptCount++;
					
					if (withdrawAmount >= 75) {
						flagged++;
						flagged_transaction();
					}
					
				}
			
			}
			catch (Exception e) {
				System.out.println("An exception was thrown getting the withdrawl.");
			}
			finally {
				//unlock
				accessLock.unlock();
			}
		}
		

	 
		
	}

	
	public void flagged_transaction() {
		
		//generate date for time-stamping
		//set the Date object format
		//create StringBuilder object
		try {
			if (isDeposit == 1) {
				//output for flagged deposit
				System.out.println("* * * FLAGGED TRANSACTION, Deposit made in excess of $350. See transacation log" );
				isDeposit = 0;
			}
			else {
				//transaction confirmed withdraw
				//make output for flagged withdraw
				System.out.println("* * * FLAGGED TRANSACTION, Withdraw made in excess of $75. See transacation log" );
				
			}
		}
		catch(Exception e) {
			System.out.println("\n Error: Problem writing to transaction file\n");
		
		}
		finally {
		
		}

	}

	
	public void internalAudit() {
		//lock
		if (accessLock.tryLock()) {
		//wait for auditor to complete
		//output thread into
			try {
				//run audit
				//signal all that audit is complete
				System.out.println("****************************************************************************************************");
				System.out.println("INTERNAL AUDITOR FINDS BALANCE TO BE: " + accountBalance + "\tNUMBER OF TRANSACTIONS SINCE LAST INTERNAL AUDIT: " + internalAuditCount);
				System.out.println("****************************************************************************************************");
				internalAuditCount = 0;
	
			}
			catch (Exception e) {
				System.out.println("An Exception was thrown getting the balance thrown by the internal auditor");
				
			}
			finally {
				//unlock bank account
				accessLock.unlock();
			}
		}
			
		
	}

	
	public void treasuryAudit() {
		//lock
		if (accessLock.tryLock()) {
		//wait for auditor to complete
		//output thread into
			try {
				//run audit
				//signal all that audit is complete
				System.out.println("****************************************************************************************************");
				System.out.println("TREASURY DEPARTMENT AUDITOR FINDS BALANCE TO BE: " + accountBalance + "\tNUMBER OF TRANSACTIONS SINCE LAST INTERNAL AUDIT: " + treasuryDeptCount);
				System.out.println("****************************************************************************************************");
				treasuryDeptCount = 0;
	
			}
			catch (Exception e) {
				System.out.println("An Exception was thrown getting the balance thrown by the treasury auditor");
				
			}
			finally {
				//unlock bank account
				accessLock.unlock();
			}
		}
		
		
	}
	
	
	
	

}
