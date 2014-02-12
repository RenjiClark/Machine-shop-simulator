package applications;

import utilities.MyInputStream;
import exceptions.MyInputException;

public class MachineList {
	private Machine[] machineList; // array of machines
	
	private int currentIndex;	
	
	public MachineList(int numMachines){
		if (numMachines < 1)
            throw new IllegalArgumentException("number of machines must be >= 1");
		
		machineList = new Machine[numMachines];
		currentIndex = 0;
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
        int t = getFinishTime(0);
        for (int i = 1; i < machineList.length; i++)
            if (getFinishTime(i) < t) {// i finishes earlier
                p = i;
                t = getFinishTime(i);
            }
        return p;
    }

    
    public int getFinishTime(int theMachine) {
        return machineList[theMachine].getFinishTime();
    }

    public void setFinishTime(int theMachine, int theTime) {
        machineList[theMachine].setFinishTime(theTime);
    }
    
	public void populateMachineList(MyInputStream keyboard) {
		System.out.println("Enter change-over times for machines");
		for (int i = 0; i < machineList.length; i++) {
			int ct = keyboard.readInteger();
			if (ct < 0)
				throw new MyInputException("change-over time must be >= 0");
			add(new Machine(ct));
		}
	}
	
	/** load first jobs onto each machine */
	public void startShop() {
		for (int p = 0; p < machineList.length; p++)
			get(p).changeState(0);
	}
}
