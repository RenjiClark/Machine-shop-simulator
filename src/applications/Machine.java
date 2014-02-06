package applications;

import dataStructures.LinkedQueue;

class Machine {
    // data members
    private LinkedQueue jobQ; // queue of waiting jobs for this machine
    private int changeTime; // machine change-over time
    private int totalWait; // total delay at this machine
    private int numTasks; // number of tasks processed on this machine
    private Job activeJob; // job currently active on this machine
    private int finishTime;

    // constructor    
    Machine(int changeTime){
    	this.jobQ = new LinkedQueue();
    	this.changeTime = changeTime;
    	finishTime = Integer.MAX_VALUE;
    }
    
	LinkedQueue getJobQ() {
		return jobQ;
	}

	Job getActiveJob() {
		return activeJob;
	}

	void setActiveJob(Job activeJob) {
		this.activeJob = activeJob;
	}

	int getChangeTime() {
		return changeTime;
	}

	int getTotalWait() {
		return totalWait;
	}

	void setTotalWait(int totalWait) {
		this.totalWait = totalWait;
	}

	int getNumTasks() {
		return numTasks;
	}

	void setNumTasks(int numTasks) {
		this.numTasks = numTasks;
	}

	void addToJobQ(Job input){
		jobQ.put(input);
	}
				
	boolean isIdle(){
		return activeJob == null;
	}
	
	boolean isJobQEmpty(){
		return jobQ.isEmpty();
	}
	
	Job removeJob(){
		return (Job) jobQ.remove();
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	
	public Job changeState(int theMachine, int timeNow) {// Task on theMachine has finished,
		// schedule next one.
		
		Job lastJob = getActiveJob();

		if (isIdle()) {// in idle or change-over
			// state
			// wait over, ready for new job
			if (isJobQEmpty()) // no waiting job
				finishTime = Integer.MAX_VALUE;
			else {// take job off the queue and work on it
				setActiveJob(removeJob());
				setTotalWait(getTotalWait() + (timeNow - getActiveJob().getArrivalTime()));
				setNumTasks(getNumTasks() + 1);
				int t = getActiveJob().removeNextTask();
				finishTime = timeNow + t;
			}
		} else {// task has just finished on currentMachine
			// schedule change-over time
			setActiveJob(null);
			finishTime = timeNow + getChangeTime();
		}

		return lastJob;
	}
	
}