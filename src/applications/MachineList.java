package applications;

public class MachineList {
	private Machine[] machineList; // array of machines
	
	@Deprecated
	private int[] finishTime; // finish time array
	private int currentIndex;	
	
	public MachineList(int numMachines){
		if (numMachines < 1)
            throw new IllegalArgumentException("number of machines must be >= 1");
		
		machineList = new Machine[numMachines];
		finishTime = new int[numMachines];
		currentIndex = 0;
		
		for (int i = 0; i < numMachines; i++){
            setFinishTime(i, Integer.MAX_VALUE);
		}
	}
	
	public Machine[] getMachine() {
		return machineList;
	}

	public void setMachine(Machine[] machine) {
		this.machineList = machine;
	}
	
	public Machine get(int index){
		return machineList[index];
	}
	
	public void add(Machine input){
		machineList[currentIndex] = input;
		currentIndex++;
	}

    /** @return machine for next event */
    int nextEventMachine() {
        // find first machine to finish, this is the
        // machine with smallest finish time
        int p = 0;
        int t = finishTime[0];
        for (int i = 1; i < machineList.length; i++)
            if (finishTime[i] < t) {// i finishes earlier
                p = i;
                t = finishTime[i];
            }
        return p;
    }

    
    int nextEventTime(int theMachine) {
        return finishTime[theMachine];
    }

    void setFinishTime(int theMachine, int theTime) {
        finishTime[theMachine] = theTime;
    }
    
    private int getFinishTime(int index){
    	return machineList[index].getFinishTime();
    }
}
