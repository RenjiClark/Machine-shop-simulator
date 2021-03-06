package applications;

import dataStructures.LinkedQueue;

class Job {
	private LinkedQueue taskQ; // this job's tasks
	private int length; // sum of scheduled task times
	private int arrivalTime; // arrival time at current queue
	private int id; // job identifier

	Job(int theId) {
		id = theId;
		taskQ = new LinkedQueue();
		// length and arrivalTime have default value 0
	}

	void addTask(int theMachine, int theTime) {
		taskQ.put(new Task(theMachine, theTime));
	}

	/**
	 * remove next task of job and return its time also update length
	 */
	int removeNextTask() {
		int theTime = ((Task) taskQ.remove()).getTime();
		length += theTime;
		return theTime;
	}

	boolean isTaskQEmpty(){
		return taskQ.isEmpty();
	}

	int getFirstMachine(){
		return ((Task) taskQ.getFrontElement()).getMachine();
	}

	int getLength() {
		return length;
	}

	int getArrivalTime() {
		return arrivalTime;
	}

	void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	int getId() {
		return id;
	}
	
}