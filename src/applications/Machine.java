package applications;

import applications.MachineShopSimulator.Job;
import dataStructures.LinkedQueue;

class Machine {
    // data members
    private LinkedQueue jobQ; // queue of waiting jobs for this machine
    int changeTime; // machine change-over time
    int totalWait; // total delay at this machine
    int numTasks; // number of tasks processed on this machine
    private Job activeJob; // job currently active on this machine

    // constructor
    Machine() {
        this.jobQ = new LinkedQueue();
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

}