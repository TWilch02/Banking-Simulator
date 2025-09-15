# Banking-Simulator
This is an application employing synchronized/cooperating multiple threads in Java using locks. Deposits and withdrawals are made to a fictitious bank account by user agents (synchronized threads).

Detailed Description:

There are 5 deposit agents, 10 withdrawal agents, and 2 auditor agents simultaneously executing. These threads are being controlled by a FixedThreadPool() and an Executor object. For simplicity sake, deposits are made in amounts ranging from $1 to $500 and withdrawals are made in amounts ranging from $1 to $99 in whole dollars ONLY. Since there are more withdrawal agents than deposit agents, the account balance should constantly decrease over time, leading to withdrawal agents constantly blocking for insufficient funds. 

The simulation is started with a balance of $0 in the account. After a depositor deposits money into the account, it is put to sleep for a few miliseconds (randomly generated time) to allow other agents to execute. All agents (depositor, withdrawal, and auditor) have the same level of priority. 

I simulated the process outlined in the Money Laundering Suppression Act of 1994, which requires banks to file Currency Transaction Reports (CTRs) for deposits of $10,000 or more. In my simulation, I flagged any deposit greater than $350.00 and any withdrawal greater than $75.00. Each flagged transaction is clearly marked in the normal simulation output and also logged into a separate transaction file (transactions.csv). This log records the transaction details, a timestamp of when it occurred, and the transaction number.

I assigned every transaction (deposit or withdrawal) a unique transaction number, starting from 1, and printed this number with each completed transaction in the simulation output.

I also implemented two auditor agents—one InternalBank auditor and one TreasuryDept auditor. These auditors do not perform transactions or affect the transaction number sequence. Instead, at random intervals, they verify the current account balance and report how many transactions have occurred since their last audit. All depositor and withdrawal agents wait while an auditor runs, ensuring auditors complete before normal activity continues. The auditors execute much less frequently than depositors or withdrawal agents, as intended.
