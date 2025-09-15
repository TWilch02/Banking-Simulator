/* Name: Terence Wilchcombe
 Course: CNT 4714 Spring 2024
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 11, 2024
*/

package project2CNT;

public interface TheBank {
	
	public abstract void deposit( );
	
	public abstract void withdraw( );
	
	public abstract void flagged_transaction( );
	
	public abstract void internalAudit( );
	
	public abstract void treasuryAudit( );

}
