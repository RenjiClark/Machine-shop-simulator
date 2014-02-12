/** machine shop simulation */

package applications;

import utilities.MyInputStream;
import exceptions.MyInputException;

public class MachineShopSimulator {

	//public static final String NUMBER_OF_MACHINES_MUST_BE_AT_LEAST_1 = "number of machines must be >= 1";
	//public static final String NUMBER_OF_MACHINES_AND_JOBS_MUST_BE_AT_LEAST_1 = "number of machines and jobs must be >= 1";
	//public static final String CHANGE_OVER_TIME_MUST_BE_AT_LEAST_0 = "change-over time must be >= 0";
	//public static final String EACH_JOB_MUST_HAVE_AT_LEAST_1_TASK = "each job must have >= 1 task";
	//public static final String BAD_MACHINE_NUMBER_OR_TASK_TIME = "bad machine number or task time";

	// data members of MachineShopSimulator
	private static int timeNow; // current time
	private static int numMachines; // number of machines
	private static int numJobs; // number of jobs
	private static MachineList machineList; // array of machines
	private static int largeTime; // all machines finish before this

	// methods
	/**
	 * move theJob to machine for its next task
	 * 
	 * @return false iff no next task
	 */

	static void moveToNextMachine(Job theJob) {
		int p = theJob.getFirstMachine();
		// put on machine p's wait queue
		machineList.get(p).addToJobQ(theJob);
		theJob.setArrivalTime(timeNow);
		// if p idle, schedule immediately
		if (machineList.getFinishTime(p) == largeTime) {// machine is idle
			machineList.get(p).changeState(timeNow);
		}

	}

	/**
	 * change the state of theMachine
	 * 
	 * @return last job run on this machine
	 */

	/** input machine shop data */
	static void inputData() {
		// define the input stream to be the standard input stream
		MyInputStream keyboard = new MyInputStream();

		System.out.println("Enter number of machines and jobs");
		numMachines = keyboard.readInteger();
		numJobs = keyboard.readInteger();
		if (numMachines < 1 || numJobs < 1){
			throw new MyInputException("number of machines and jobs must be >= 1");
		}

		// create event and machine queues
		machineList = new MachineList(numMachines);
		machineList.populateMachineList(keyboard);
		inputJobData(keyboard);
	}

	private static void inputJobData(MyInputStream keyboard) {
		for (int i = 0; i < numJobs; i++) {
			System.out.println("Enter number of tasks for job " + (i+1));
			int tasks = keyboard.readInteger(); // number of tasks
			if (tasks < 1)
				throw new MyInputException("each job must have >= 1 task");

			// create the job
			Job theJob = new Job(i);
			int firstMachine = 0; // machine for first task

			System.out.println("Enter the tasks (machine, time) in process order");
			for (int j = 0; j < tasks; j++) {// get tasks for job i
				int theMachine = keyboard.readInteger()-1;
				int theTaskTime = keyboard.readInteger();

				if (theMachine < 0 || theMachine > numMachines || theTaskTime < 1){
					throw new MyInputException("bad machine number or task time");
				}

				if (j == 0) firstMachine = theMachine; // job's first machine

				theJob.addTask(theMachine, theTaskTime); // add to
			} // task queue
			machineList.get(firstMachine).addToJobQ(theJob);
		}
	}



	/** process all jobs to completion */
	static void simulate() {
		while (numJobs > 0) {// at least one job left
			int nextToFinish = machineList.nextEventMachine();
			timeNow = machineList.getFinishTime(nextToFinish);
			// change job on machine nextToFinish
			Job theJob = machineList.get(nextToFinish).changeState(timeNow);
			// move theJob to its next machine
			// decrement numJobs if theJob has finished

			if(theJob != null){
				if(theJob.isTaskQEmpty()){
					System.out.println("Job " + (theJob.getId()+1) + " has completed at "
							+ timeNow + " Total wait was " + (timeNow - theJob.getLength()));
					numJobs--;
				} else{
					moveToNextMachine(theJob);
				}
			}

			//			if (theJob != null && !moveToNextMachine(theJob)) //!machineList.get(theJob.getFirstMachine()).moveToNextMachine(theJob)
			//				numJobs--;
		}
	}

	/** output wait times at machines */
	static void outputStatistics() {
		System.out.println("Finish time = " + timeNow);
		for (int p = 0; p < numMachines; p++) {
			int realIndex = p+1;
			System.out.println("Machine " + realIndex + " completed "
					+ machineList.get(p).getNumTasks() + " tasks");
			System.out.println("The total wait time was "
					+ machineList.get(p).getTotalWait());
			System.out.println();
		}
	}

	/** entry point for machine shop simulator */
	public static void main(String[] args) {
		largeTime = Integer.MAX_VALUE;
		/*
		 * It's vital that we (re)set this to 0 because if the simulator is called
		 * multiple times (as happens in the acceptance tests), because timeNow
		 * is static it ends up carrying over from the last time it was run. I'm
		 * not convinced this is the best place for this to happen, though.
		 */
		timeNow = 0;
		inputData(); // get machine and job data
		machineList.startShop(); // initial machine loading
		simulate(); // run all jobs through shop
		outputStatistics(); // output machine wait times
	}
}
