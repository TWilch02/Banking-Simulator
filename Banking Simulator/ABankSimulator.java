/* Name: Terence Wilchcombe
 Course: CNT 4714 Spring 2024
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 11, 2024
*/

package project2CNT;  

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ABankSimulator {
	
	public static final int MAX_AGENTS = 17;

	public static void main(String[] args) {
			
		ExecutorService application = Executors.newFixedThreadPool(MAX_AGENTS);
		TheBankAccount jointAccount = new TheBankAccount();
		try {
			System.out.println("* * * SIMULATION BEGINS....\n");
			System.out.println("Deposit Agents\t\t\t    Withdrawl Agents\t\t     Balance                                   Transaction Number");
			System.out.println("--------------\t\t\t    ----------------\t\t-------------------\t\t\t\t\t---------------------");
			
			application.execute( new Withdrawl(jointAccount, "Agent WT1"));
			application.execute( new Withdrawl(jointAccount, "Agent WT2"));
			application.execute( new Withdrawl(jointAccount, "Agent WT3"));
			application.execute( new Withdrawl(jointAccount, "Agent WT4"));
			application.execute( new Withdrawl(jointAccount, "Agent WT5")); 
			application.execute( new Withdrawl(jointAccount, "Agent WT6")); 
			application.execute( new Withdrawl(jointAccount, "Agent WT7")); 
			application.execute( new Withdrawl(jointAccount, "Agent WT8"));
			application.execute( new Withdrawl(jointAccount, "Agent WT9")); 
			application.execute( new Withdrawl(jointAccount, "Agent WT10")); 
			application.execute( new Depositor(jointAccount, "Agent DT1"));
			application.execute( new Depositor(jointAccount, "Agent DT2"));
			application.execute( new Depositor(jointAccount, "Agent DT3"));
			application.execute( new Depositor(jointAccount, "Agent DT4"));
			application.execute( new Depositor(jointAccount, "Agent DT5"));
			application.execute( new InternalAuditor(jointAccount, "INTERNAL BANK AUDITOR"));
			application.execute( new TreasuryDeptAuditor(jointAccount, "TREASURY DEPT AUDITOR"));
			
		}
		catch ( Exception exception ){
			exception.printStackTrace();
			
		}
		application.shutdown();


	}

}
