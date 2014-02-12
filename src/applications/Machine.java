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

	public int getTotalWait() {
		return totalWait;
	}
	
	public int getNumTasks(){
		return numTasks;
	}

	public void addToJobQ(Job input){
		jobQ.put(input);
	}

	private boolean isIdle(){
		return activeJob == null;
	}

	private Job removeJob(){
		return (Job) jobQ.remove();
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public Job changeState(int timeNow) {// Task on theMachine has finished,
		// schedule next one.

		Job lastJob = activeJob;

		if (isIdle()) {// in idle or change-over
			// state
			// wait over, ready for new job
			if (jobQ.isEmpty()) // no waiting job
				finishTime = Integer.MAX_VALUE;
			else {// take job off the queue and work on it
				activeJob = removeJob();
				totalWait += (timeNow - activeJob.getArrivalTime());
				numTasks += 1;
				int t = activeJob.removeNextTask();
				finishTime = timeNow + t;
			}
		} else {// task has just finished on currentMachine
			// schedule change-over time
			activeJob = null;
			finishTime = timeNow + changeTime;
		}

		return lastJob;
	}
	
}