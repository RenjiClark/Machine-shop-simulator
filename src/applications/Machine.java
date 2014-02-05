package applications;

import dataStructures.LinkedQueue;

class Machine {
    // data members
    private LinkedQueue jobQ; // queue of waiting jobs for this machine
    private int changeTime; // machine change-over time
    private int totalWait; // total delay at this machine
    private int numTasks; // number of tasks processed on this machine
    private Job activeJob; // job currently active on this machine

    // constructor
    Machine() {
        this.jobQ = new LinkedQueue();
    }
    
    Machine(int changeTime){
    	this.jobQ = new LinkedQueue();
    	this.changeTime = changeTime;
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

	void setChangeTime(int changeTime) {
		this.changeTime = changeTime;
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

	
}