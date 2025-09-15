/* Name: Terence Wilchcombe
 Course: CNT 4714 Spring 2024
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 11, 2024
*/

package project2CNT;
import java.util.Random;

public class Withdrawl implements Runnable{
	
	private static final int MAX_WITHDRAW = 100;
	private static final int MAXSLEEPTIME = 2000;
	private String agentName = "";
	Random sleeptime = new Random();
	TheBankAccount theBankAccount = new TheBankAccount();
	
	public Withdrawl (TheBankAccount jointAccount, String string ) {
		
		agentName = string;
		theBankAccount = jointAccount;
	
	}
	
	//take money from account
	
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(sleeptime.nextInt(MAXSLEEPTIME-1+1)+1);
				
				System.out.println(agentName + "  ");
				theBankAccount.withdraw();
			}
			catch( InterruptedException exception) {
				
				System.out.println("Exception thrown while withdrawing");
				
				
			}
		}
	}

	

}
