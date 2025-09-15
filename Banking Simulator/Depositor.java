/* Name: Terence Wilchcombe
 Course: CNT 4714 Spring 2024
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 11, 2024
*/

package project2CNT;
import java.util.Random;

public class Depositor implements Runnable{
	
	private static final int MAX_DEPOSIT = 500;
	private static final int MAXSLEEPTIME = 5000;
	Random sleeptime = new Random();
	TheBankAccount theBankAccount = new TheBankAccount();
	private  String agentName = "";
	
	public Depositor(TheBankAccount jointAccount, String string ) {
	
		agentName = string;
		theBankAccount = jointAccount;
	}
	
	//add money to account
	
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(sleeptime.nextInt(MAXSLEEPTIME-1+1)+1);
				System.out.println(agentName + "  ");
				theBankAccount.deposit();
				System.out.println(""); 
			}
			catch( InterruptedException exception) {
				
				System.out.println("Exception thrown while depositing");
				
				
			}
		}
	}

}
